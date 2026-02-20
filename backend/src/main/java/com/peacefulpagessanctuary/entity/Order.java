package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "orders_id")
    private UUID id;

    @Column(name = "orders_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @Column(name = "orders_status")
    private String status;

    @Column(name = "orders_total")
    private BigDecimal total;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @ManyToOne
    @JoinColumn(name = "coupon_code")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "couponship_code")
    private CouponShip couponShip;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}