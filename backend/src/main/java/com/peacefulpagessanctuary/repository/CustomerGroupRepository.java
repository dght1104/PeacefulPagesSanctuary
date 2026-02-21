package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.entity.CustomerGroup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {
    Optional<CustomerGroup> findByDescription(String description);
}