package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.Catalogue;

import java.util.List;
import java.util.Optional;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
    Optional<Catalogue> findByName(String name);

    boolean existsByName(String name);

    List<Catalogue> findByNameContaining(String keyword);
}