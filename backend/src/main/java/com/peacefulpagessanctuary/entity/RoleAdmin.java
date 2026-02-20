package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true)
    private String roleName;
}