package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}