package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "coupon_ship")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponShip {

    @Id
    @Column(name = "couponship_code")
    private String code;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value")
    private BigDecimal discountValue;

    @Column(name = "min_order_value")
    private BigDecimal minOrderValue;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "used_count")
    private Integer usedCount;

    @Column(name = "status")
    private String status;

    @Column(name = "customer_group")
    private String customerGroup;
}