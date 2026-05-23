package com.peacefulpagessanctuary.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    public Optional<Coupon> findByCode(String code);
}