package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(name = "admin_name")
    private String name;

    @Column(name = "admin_username", unique = true)
    private String username;

    @Column(name = "admin_password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleAdmin role;
}