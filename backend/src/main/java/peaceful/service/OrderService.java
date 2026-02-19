package peaceful.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaceful.dto.CheckoutRequest;
import peaceful.model.*;
import peaceful.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public String checkout(CheckoutRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<CartItem> cartItems = cartRepository.findByCustomerId(customer.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal total = BigDecimal.ZERO;

        // 1️⃣ CHECK STOCK
        for (CartItem item : cartItems) {

            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int availableStock = product.getReceived() - product.getSold();

            if (item.getQuantity() > availableStock) {
                throw new RuntimeException(
                        "Not enough stock for product: " + product.getName()
                );
            }

            BigDecimal priceAfterDiscount =
                    product.getPrice().subtract(
                            product.getPrice()
                                    .multiply(product.getDiscount())
                                    .divide(BigDecimal.valueOf(100))
                    );

            total = total.add(
                    priceAfterDiscount.multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    )
            );
        }

        // 2️⃣ APPLY COUPON
        Coupon coupon = null;

        if (request.getCouponCode() != null) {

            coupon = couponRepository
                    .findByCodeAndStatus(request.getCouponCode(), "active")
                    .orElseThrow(() -> new RuntimeException("Invalid coupon"));

            if (coupon.getUsageLimit() != null &&
                    coupon.getUsedCount() >= coupon.getUsageLimit()) {
                throw new RuntimeException("Coupon usage limit reached");
            }

            if ("percentage".equalsIgnoreCase(coupon.getDiscountType())) {

                BigDecimal discount =
                        total.multiply(coupon.getDiscountValue())
                                .divide(BigDecimal.valueOf(100));

                total = total.subtract(discount);

            } else {

                total = total.subtract(coupon.getDiscountValue());
            }

            coupon.setUsedCount(coupon.getUsedCount() + 1);
            couponRepository.save(coupon);
        }

        // 3️⃣ CREATE ORDER
        Order order = Order.builder()
                .id(UUID.randomUUID().toString().replace("-", "").substring(0, 10))
                .date(LocalDate.now())
                .customer(customer)
                .status("pending")
                .total(total)
                .shippingFee(BigDecimal.ZERO)
                .coupon(coupon)
                .build();

        orderRepository.save(order);

        // 4️⃣ SAVE ORDER DETAILS + UPDATE STOCK
        for (CartItem item : cartItems) {

            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow();

            product.setSold(product.getSold() + item.getQuantity());
            productRepository.save(product);

            OrderDetail detail = OrderDetail.builder()
                    .order(order)
                    .product(product)
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .build();

            orderDetailRepository.save(detail);
        }

        // 5️⃣ CLEAR CART
        cartRepository.clearCart(customer.getId());

        return "Checkout successful. Order ID: " + order.getId();
    }
}
