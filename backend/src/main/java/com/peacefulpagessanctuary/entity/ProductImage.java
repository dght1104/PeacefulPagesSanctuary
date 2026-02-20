package com.peacefulpagessanctuary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @Column(name = "is_primary")
    private Boolean primaryImage;
}