package com.peacefulpagessanctuary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peacefulpagessanctuary.model.Catalogue;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}