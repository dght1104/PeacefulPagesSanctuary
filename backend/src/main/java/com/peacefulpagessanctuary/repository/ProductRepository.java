package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.Catalogue;
import com.peacefulpagessanctuary.model.Product;
import com.peacefulpagessanctuary.model.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String keyword);

    List<Product> findByCatalogue(Catalogue catalogue);

    List<Product> findBySupplier(Supplier supplier);

    List<Product> findByPriceBetween(
            BigDecimal minPrice,
            BigDecimal maxPrice
    );

    List<Product> findByDiscountGreaterThan(BigDecimal discount);

    List<Product> findBySoldGreaterThan(Integer sold);

}