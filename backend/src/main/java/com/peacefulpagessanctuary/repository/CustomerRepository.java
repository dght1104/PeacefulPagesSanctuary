package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.Customer;
import com.peacefulpagessanctuary.model.CustomerGroup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<Customer> findByNameContaining(String keyword);

    List<Customer> findByActive(Boolean active);

    List<Customer> findByVerified(Boolean verified);

    List<Customer> findByCustomerGroup(CustomerGroup customerGroup);

}