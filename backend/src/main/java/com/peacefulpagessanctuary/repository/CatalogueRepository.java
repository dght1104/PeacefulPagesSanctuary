package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}