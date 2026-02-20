package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.entity.*;
import com.peacefulpagessanctuary.exception.*;
import com.peacefulpagessanctuary.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);

        if (cartItems.isEmpty()) {
            throw new InvalidOperationException("Cart is empty");
        }

        long subtotal = 0L;
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (CartItem item : cartItems) {

            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            long availableStock = product.getProdReceived() - product.getProdSold();

            if (availableStock < item.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: "
                        + product.getName());
            }

            long discountedPrice = product.getPrice();

            if (product.getDiscountPercent() != null) {
                discountedPrice = discountedPrice -
                        (discountedPrice * product.getDiscountPercent() / 100);
            }

            long itemTotal = discountedPrice * item.getQuantity();
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

            if (productCoupon.getPercentage() != null) {
                productCouponDiscount =
                        subtotal * productCoupon.getPercentage() / 100;
            } else {
                productCouponDiscount = productCoupon.getFixedAmount();
            }

            if (productCouponDiscount > subtotal) {
                productCouponDiscount = subtotal;
            }
        }

        if (shippingCouponCode != null) {
            shippingCoupon = couponService.validateShippingCoupon(
                    shippingCouponCode, customer, subtotal);

            if (shippingCoupon.getPercentage() != null) {
                shippingCouponDiscount =
                        shippingFee * shippingCoupon.getPercentage() / 100;
            } else {
                shippingCouponDiscount = shippingCoupon.getFixedAmount();
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
        order.setTotalAmount(finalTotal);

        orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {

            Product product = detail.getProduct();

            product.setProdSold(product.getProdSold() + detail.getQuantity());

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