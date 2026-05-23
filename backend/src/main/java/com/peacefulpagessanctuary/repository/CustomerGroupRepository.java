package com.peacefulpagessanctuary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.CustomerGroup;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {
    Optional<CustomerGroup> findByDescription(String description);
}