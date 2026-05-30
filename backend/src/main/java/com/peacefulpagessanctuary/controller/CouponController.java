package com.peacefulpagessanctuary.controller;
import com.peacefulpagessanctuary.dto.request.coupon.CouponRequest;
import com.peacefulpagessanctuary.dto.response.coupon.CouponResponse;
import com.peacefulpagessanctuary.service.CouponService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    /**
     * Lấy danh sách coupon
     */
    @GetMapping
    public List<CouponResponse> getAllCoupons() {

        return couponService.getAllCoupons();
    }

    /**
     * Lấy coupon theo ID
     */
    @GetMapping("/{id}")
    public CouponResponse getCouponById(
            @PathVariable Long id) {

        return couponService.getCouponById(id);
    }

    /**
     * Tạo coupon
     */
    @PostMapping
    public CouponResponse createCoupon(
            @RequestBody CouponRequest request) {

        return couponService.createCoupon(request);
    }

    /**
     * Cập nhật coupon
     */
    @PutMapping("/{id}")
    public CouponResponse updateCoupon(
            @PathVariable Long id,
            @RequestBody CouponRequest request) {

        return couponService.updateCoupon(id, request);
    }

    /**
     * Xóa coupon
     */
    @DeleteMapping("/{id}")
    public void deleteCoupon(
            @PathVariable Long id) {

        couponService.deleteCoupon(id);
    }

    /**
     * Áp dụng coupon theo mã
     */
    @GetMapping("/apply/{code}")
    public CouponResponse applyCoupon(
            @PathVariable String code) {

        return couponService.applyCoupon(code);
    }
}