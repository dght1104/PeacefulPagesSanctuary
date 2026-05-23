package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.enums.CouponStatus;
import com.peacefulpagessanctuary.enums.CouponType;
import com.peacefulpagessanctuary.model.Coupon;
import com.peacefulpagessanctuary.model.CustomerGroup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    List<Coupon> findByStatus(CouponStatus status);

    List<Coupon> findByCouponType(CouponType couponType);

    List<Coupon> findByCustomerGroup(CustomerGroup customerGroup);

    boolean existsByCode(String code);

    List<Coupon> findByEndDateAfter(LocalDate date);

}