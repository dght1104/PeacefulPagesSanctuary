package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCustomerId(Long customerId);

    Optional<CartItem> findByCustomerIdAndProductId(Long customerId, Long productId);

    void deleteByCustomerId(Long customerId);
}