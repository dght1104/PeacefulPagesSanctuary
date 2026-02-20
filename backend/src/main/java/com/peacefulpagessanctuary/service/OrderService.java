package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.entity.*;
import com.peacefulpagessanctuary.exception.*;
import com.peacefulpagessanctuary.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CouponService couponService;
    private final CouponRepository couponRepository;
    private final CouponShipRepository couponShipRepository;
    private final CustomerGroupRepository customerGroupRepository;

    public OrderService(CustomerRepository customerRepository,
                        CartItemRepository cartItemRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        CouponService couponService,
                        CouponRepository couponRepository,
                        CouponShipRepository couponShipRepository,
                        CustomerGroupRepository customerGroupRepository) {
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.couponService = couponService;
        this.couponRepository = couponRepository;
        this.couponShipRepository = couponShipRepository;
        this.customerGroupRepository = customerGroupRepository;
    }

    @Transactional
    public Order checkout(String customerEmail,
                          String productCouponCode,
                          String shippingCouponCode,
                          long shippingFee) {

        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customer.getId());

        if (cartItems.isEmpty()) {
            throw new InvalidOperationException("Cart is empty");
        }

        long subtotal = 0L;
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (CartItem item : cartItems) {

            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            long availableStock = product.getReceived() - product.getSold();

            if (availableStock < item.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: "
                        + product.getName());
            }

            BigDecimal discountedPrice = product.getPrice();

            if (product.getDiscount() != null) {
                discountedPrice = discountedPrice.subtract(
                        discountedPrice.multiply(product.getDiscount())
                                .divide(BigDecimal.valueOf(100), 2, java.math.RoundingMode.HALF_UP)
                );
            }

            long itemTotal = discountedPrice.multiply(BigDecimal.valueOf(item.getQuantity())).longValue();
            subtotal += itemTotal;

            OrderDetail detail = new OrderDetail();
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setPrice(discountedPrice);

            orderDetails.add(detail);
        }

        long productCouponDiscount = 0L;
        long shippingCouponDiscount = 0L;

        Coupon productCoupon = null;
        CouponShip shippingCoupon = null;

        if (productCouponCode != null) {
            productCoupon = couponService.validateProductCoupon(
                    productCouponCode, customer, subtotal);

            if ("PERCENT".equalsIgnoreCase(productCoupon.getDiscountType())) {

                productCouponDiscount = BigDecimal.valueOf(subtotal)
                        .multiply(productCoupon.getDiscountValue())
                        .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
                        .longValue();

            } else if ("FIXED".equalsIgnoreCase(productCoupon.getDiscountType())) {

                productCouponDiscount = productCoupon.getDiscountValue()
                        .setScale(0, RoundingMode.HALF_UP)
                        .longValue();
            }
        }

        if (shippingCouponCode != null) {
            shippingCoupon = couponService.validateShippingCoupon(
                    shippingCouponCode, customer, subtotal);

             if ("PERCENT".equalsIgnoreCase(shippingCoupon.getDiscountType())) {

                shippingCouponDiscount = BigDecimal.valueOf(shippingFee)
                        .multiply(shippingCoupon.getDiscountValue())
                        .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP)
                        .longValue();

            } else if ("FIXED".equalsIgnoreCase(shippingCoupon.getDiscountType())) {

                shippingCouponDiscount = shippingCoupon.getDiscountValue()
                        .setScale(0, RoundingMode.HALF_UP)
                        .longValue();
            }

            if (shippingCouponDiscount > shippingFee) {
                shippingCouponDiscount = shippingFee;
            }
        }

        long finalTotal =
                subtotal
                - productCouponDiscount
                - shippingCouponDiscount
                + shippingFee;

        if (finalTotal < 0) {
            finalTotal = 0;
        }

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomer(customer);
        order.setTotal(null);

        orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {

            Product product = detail.getProduct();

            product.setSold(product.getSold() + detail.getQuantity());

            detail.setOrder(order);
            orderDetailRepository.save(detail);
        }

        if (productCoupon != null) {
            couponService.incrementProductCouponUsage(productCoupon);
        }

        if (shippingCoupon != null) {
            couponService.incrementShippingCouponUsage(shippingCoupon);
        }

        customer.setTotalSpent(customer.getTotalSpent() + finalTotal);

        CustomerGroup newGroup;

        if (customer.getTotalSpent() >= 25_000_000L) {
            newGroup = customerGroupRepository.findByName("Platinum")
                    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
        } else if (customer.getTotalSpent() >= 15_000_000L) {
            newGroup = customerGroupRepository.findByName("Gold")
                    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
        } else {
            newGroup = customerGroupRepository.findByName("Silver")
                    .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
        }

        customer.setCustomerGroup(newGroup);

        cartItemRepository.deleteByCustomer(customer);

        return order;
    }
}