package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.coupon.CouponRequest;
import com.peacefulpagessanctuary.dto.response.coupon.CouponResponse;

import com.peacefulpagessanctuary.exception.InvalidOperationException;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;

import com.peacefulpagessanctuary.mapper.CouponMapper;

import com.peacefulpagessanctuary.model.Coupon;
import com.peacefulpagessanctuary.model.CustomerGroup;

import com.peacefulpagessanctuary.repository.CouponRepository;
import com.peacefulpagessanctuary.repository.CustomerGroupRepository;

import com.peacefulpagessanctuary.service.CouponService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public List<CouponResponse> getAllCoupons() {

        return couponRepository.findAll()
                .stream()
                .map(couponMapper::toResponse)
                .toList();
    }

    @Override
    public CouponResponse getCouponById(Long id) {

        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        return couponMapper.toResponse(coupon);
    }

    @Override
    public CouponResponse createCoupon(CouponRequest request) {

        CustomerGroup group = null;

        if (request.getCustomerGroupId() != null) {
            group = customerGroupRepository.findById(request.getCustomerGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer group not found"));
        }

        Coupon coupon = couponMapper.toEntity(request, group);

        Coupon savedCoupon = couponRepository.save(coupon);

        return couponMapper.toResponse(savedCoupon);
    }

    @Override
    public CouponResponse updateCoupon(Long id, CouponRequest request) {

        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        CustomerGroup group = null;

        if (request.getCustomerGroupId() != null) {
            group = customerGroupRepository.findById(request.getCustomerGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer group not found"));
        }

        couponMapper.updateEntity(coupon, request, group);

        Coupon updatedCoupon = couponRepository.save(coupon);

        return couponMapper.toResponse(updatedCoupon);
    }

    @Override
    public void deleteCoupon(Long id) {

        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        couponRepository.delete(coupon);
    }

    @Override
    public CouponResponse applyCoupon(String couponCode) {

        Coupon coupon = couponRepository.findByCode(couponCode)
                .orElseThrow(() -> new InvalidOperationException("Invalid coupon code"));

        // optional: check expiry
        if (coupon.getEndDate() != null &&
                coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new InvalidOperationException("Coupon expired");
        }

        return couponMapper.toResponse(coupon);
    }
}