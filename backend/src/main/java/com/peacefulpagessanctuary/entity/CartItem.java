package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitem_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;
}