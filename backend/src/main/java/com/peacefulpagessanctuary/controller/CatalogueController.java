package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.entity.Catalogue;
import com.peacefulpagessanctuary.repository.CatalogueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogues")
public class CatalogueController {

    private final CatalogueRepository catalogueRepository;

    public CatalogueController(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @GetMapping
    public List<Catalogue> getAllCatalogues() {
        return catalogueRepository.findAll();
    }
}