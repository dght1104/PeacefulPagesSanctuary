package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_name")
    private String name;

    @Column(name = "prod_received")
    private Integer received;

    @Column(name = "prod_sold")
    private Integer sold;

    @Column(name = "prod_price")
    private BigDecimal price;

    @Column(name = "prod_discount")
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Catalogue catalogue;

    @ManyToOne
    @JoinColumn(name = "supply_id")
    private Supplier supplier;

    @Column(name = "prod_description")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;
}