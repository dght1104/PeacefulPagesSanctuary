package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {
}