package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.coupon.CouponRequest;
import com.peacefulpagessanctuary.dto.response.coupon.CouponResponse;
import com.peacefulpagessanctuary.enums.CouponStatus;
import com.peacefulpagessanctuary.model.Coupon;
import com.peacefulpagessanctuary.model.CustomerGroup;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CouponMapper {

    public Coupon toEntity(
            CouponRequest request,
            CustomerGroup customerGroup
    ) {

        return Coupon.builder()
                .code(request.getCode())
                .couponType(request.getCouponType())
                .discountType(request.getDiscountType())
                .discountValue(request.getDiscountValue())
                .minOrderValue(
                        request.getMinOrderValue() != null
                                ? request.getMinOrderValue()
                                : BigDecimal.ZERO
                )
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .usageLimit(request.getUsageLimit())
                .usedCount(0)
                .status(CouponStatus.ACTIVE)
                .customerGroup(customerGroup)
                .build();
    }

    public CouponResponse toResponse(Coupon coupon) {

        return CouponResponse.builder()
                .code(coupon.getCode())
                .couponType(coupon.getCouponType())
                .discountType(coupon.getDiscountType())
                .discountValue(coupon.getDiscountValue())
                .minOrderValue(coupon.getMinOrderValue())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .usageLimit(coupon.getUsageLimit())
                .usedCount(coupon.getUsedCount())
                .status(coupon.getStatus())
                .customerGroupName(
                        coupon.getCustomerGroup() != null
                                ? coupon.getCustomerGroup()
                                        .getDescription()
                                : null
                )
                .build();
    }

    public void updateEntity(
            Coupon coupon,
            CouponRequest request,
            CustomerGroup customerGroup
    ) {

        coupon.setCouponType(request.getCouponType());
        coupon.setDiscountType(request.getDiscountType());
        coupon.setDiscountValue(request.getDiscountValue());

        coupon.setMinOrderValue(
                request.getMinOrderValue() != null
                        ? request.getMinOrderValue()
                        : BigDecimal.ZERO
        );

        coupon.setStartDate(request.getStartDate());
        coupon.setEndDate(request.getEndDate());
        coupon.setUsageLimit(request.getUsageLimit());
        coupon.setCustomerGroup(customerGroup);
    }
}