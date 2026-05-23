package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.ProductImage;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductId(Long productId);
}