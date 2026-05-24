package com.peacefulpagessanctuary.dto.response.order;

import com.peacefulpagessanctuary.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private LocalDateTime orderDate;

    private String customerName;

    private OrderStatus status;

    private BigDecimal total;

    private BigDecimal shippingFee;

    private String couponCode;

    private String shippingCouponCode;

    private List<OrderDetailResponse> orderDetails;
}