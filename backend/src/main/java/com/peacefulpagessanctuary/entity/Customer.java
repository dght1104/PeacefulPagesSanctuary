package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Long id;

    @Column(name = "cus_name")
    private String name;

    @Column(name = "cus_email", unique = true)
    private String email;

    @Column(name = "cus_phone")
    private String phone;

    @Column(name = "cus_username", unique = true)
    private String username;

    @Column(name = "cus_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private CustomerGroup customerGroup;

    @Column(name = "is_verified")
    private Boolean verified;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "total_spent")
    private BigDecimal totalSpent;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}