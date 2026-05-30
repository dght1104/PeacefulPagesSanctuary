package com.peacefulpagessanctuary.dto.request.coupon;

import com.peacefulpagessanctuary.enums.CouponType;
import com.peacefulpagessanctuary.enums.DiscountType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponRequest {

    private String code;

    private CouponType couponType;

    private DiscountType discountType;

    private BigDecimal discountValue;

    private BigDecimal minOrderValue;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer usageLimit;

    private Long customerGroupId;
}