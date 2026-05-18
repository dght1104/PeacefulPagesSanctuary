package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "cus_phone", length = 10)
    private String phone;

    @Column(name = "cus_username", unique = true)
    private String username;

    @Column(name = "cus_password")
    private String password;

    @Column(name = "cus_img", length = 256)
    private String image;

    @Column(name = "cus_address", length = 256)
    private String address;

    @Column(name = "cus_dob")
    private LocalDate dateOfBirth;

    // FK -> customer_groups
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private CustomerGroup customerGroup;

    @Column(name = "is_verified")
    private Boolean verified = false;

    @Column(name = "is_active", nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Order> orders;
}