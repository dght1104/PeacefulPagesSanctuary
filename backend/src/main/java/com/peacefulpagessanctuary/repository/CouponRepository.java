package com.peacefulpagessanctuary.repository;
import java.util.Optional;
import com.peacefulpagessanctuary.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {
    public Optional<Coupon> findByCode(String code);
}