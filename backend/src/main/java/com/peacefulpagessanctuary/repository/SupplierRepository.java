package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.Supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByName(String name);

    boolean existsByName(String name);

    List<Supplier> findByNameContaining(String keyword);

}