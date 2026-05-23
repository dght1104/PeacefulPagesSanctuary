package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.Product;
import com.peacefulpagessanctuary.model.ProductImage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProduct(Product product);

    Optional<ProductImage> findByProductAndIsPrimary(
            Product product,
            Boolean isPrimary
    );

    List<ProductImage> findByIsPrimary(Boolean isPrimary);

}