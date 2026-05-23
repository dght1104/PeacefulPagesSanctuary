package com.peacefulpagessanctuary.model;

import com.peacefulpagessanctuary.enums.CouponStatus;
import com.peacefulpagessanctuary.enums.CouponType;
import com.peacefulpagessanctuary.enums.DiscountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon extends BaseEntity {

    @Id
    @Column(name = "coupon_code", length = 50)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type", nullable = false)
    private CouponType couponType;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    @Builder.Default
    @Column(name = "min_order_value")
    private BigDecimal minOrderValue = BigDecimal.ZERO;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Builder.Default
    @Column(name = "used_count")
    private Integer usedCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CouponStatus status = CouponStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private CustomerGroup customerGroup;
}