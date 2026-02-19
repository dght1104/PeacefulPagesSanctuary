package peaceful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaceful.model.Coupon;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, String> {

    Optional<Coupon> findByCodeAndStatus(String code, String status);
}
