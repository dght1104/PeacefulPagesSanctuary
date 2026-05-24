package com.peacefulpagessanctuary.dto.request.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutRequest {

    private String couponCode;

    private String shippingCouponCode;

    private String shippingAddress;

    private String paymentMethod;
}