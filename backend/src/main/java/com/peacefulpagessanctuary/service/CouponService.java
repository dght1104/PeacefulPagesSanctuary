package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.entity.Coupon;
import com.peacefulpagessanctuary.entity.CouponShip;
import com.peacefulpagessanctuary.entity.Customer;
import com.peacefulpagessanctuary.exception.CouponInvalidException;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;
import com.peacefulpagessanctuary.repository.CouponRepository;
import com.peacefulpagessanctuary.repository.CouponShipRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponShipRepository couponShipRepository;

    public CouponService(CouponRepository couponRepository,
                         CouponShipRepository couponShipRepository) {
        this.couponRepository = couponRepository;
        this.couponShipRepository = couponShipRepository;
    }

    public Coupon validateProductCoupon(String code, Customer customer, long subtotal) {

        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found"));

        if (!coupon.isActive()) {
            throw new CouponInvalidException("Coupon inactive");
        }

        LocalDate today = LocalDate.now();

        if (today.isBefore(coupon.getStartDate()) ||
            today.isAfter(coupon.getEndDate())) {
            throw new CouponInvalidException("Coupon expired");
        }

        if (coupon.getUsageLimit() != null &&
            coupon.getUsedCount() >= coupon.getUsageLimit()) {
            throw new CouponInvalidException("Coupon usage limit reached");
        }

        if (subtotal < coupon.getMinOrderValue()) {
            throw new CouponInvalidException("Minimum order value not satisfied");
        }

        if (coupon.getCustomerGroup() != null &&
            !coupon.getCustomerGroup().getId()
                .equals(customer.getCustomerGroup().getId())) {
            throw new CouponInvalidException("Customer group not eligible");
        }

        return coupon;
    }

    public CouponShip validateShippingCoupon(String code, Customer customer, long subtotal) {

        CouponShip coupon = couponShipRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Shipping coupon not found"));

        if (!coupon.isActive()) {
            throw new CouponInvalidException("Shipping coupon inactive");
        }

        LocalDate today = LocalDate.now();

        if (today.isBefore(coupon.getStartDate()) ||
            today.isAfter(coupon.getEndDate())) {
            throw new CouponInvalidException("Shipping coupon expired");
        }

        if (coupon.getUsageLimit() != null &&
            coupon.getUsedCount() >= coupon.getUsageLimit()) {
            throw new CouponInvalidException("Shipping coupon usage limit reached");
        }

        if (subtotal < coupon.getMinOrderValue()) {
            throw new CouponInvalidException("Minimum order value not satisfied");
        }

        if (coupon.getCustomerGroup() != null &&
            !coupon.getCustomerGroup().getId()
                .equals(customer.getCustomerGroup().getId())) {
            throw new CouponInvalidException("Customer group not eligible");
        }

        return coupon;
    }

    public void incrementProductCouponUsage(Coupon coupon) {
        coupon.setUsedCount(coupon.getUsedCount() + 1);
    }

    public void incrementShippingCouponUsage(CouponShip coupon) {
        coupon.setUsedCount(coupon.getUsedCount() + 1);
    }
}