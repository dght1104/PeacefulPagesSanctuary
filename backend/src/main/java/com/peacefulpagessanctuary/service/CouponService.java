package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.coupon.CouponRequest;
import com.peacefulpagessanctuary.dto.response.coupon.CouponResponse;

import java.util.List;

public interface CouponService {

    List<CouponResponse> getAllCoupons();

    CouponResponse getCouponById(Long id);

    CouponResponse createCoupon(CouponRequest request);

    CouponResponse updateCoupon(Long id, CouponRequest request);

    void deleteCoupon(Long id);

    CouponResponse applyCoupon(String couponCode);
}