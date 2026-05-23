package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.CartItem;
import com.peacefulpagessanctuary.model.Customer;
import com.peacefulpagessanctuary.model.Product;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCustomer(Customer customer);

    Optional<CartItem> findByCustomerAndProduct(Customer customer, Product product);

    void deleteByCustomer(Customer customer);

    boolean existsByCustomerAndProduct(Customer customer, Product product);
}