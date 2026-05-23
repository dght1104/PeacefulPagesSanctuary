package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}