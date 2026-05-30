package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.catalogue.CatalogueRequest;
import com.peacefulpagessanctuary.dto.response.catalogue.CatalogueResponse;
import com.peacefulpagessanctuary.service.CatalogueService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogues")
@RequiredArgsConstructor
public class CatalogueController {

    private final CatalogueService catalogueService;

    /**
     * Lấy tất cả danh mục
     */
    @GetMapping
    public List<CatalogueResponse> getAllCatalogues() {

        return catalogueService.getAllCatalogues();
    }

    /**
     * Lấy danh mục theo ID
     */
    @GetMapping("/{id}")
    public CatalogueResponse getCatalogueById(
            @PathVariable Long id) {

        return catalogueService.getCatalogueById(id);
    }

    /**
     * Tạo danh mục mới
     */
    @PostMapping
    public CatalogueResponse createCatalogue(
            @Valid @RequestBody CatalogueRequest request) {

        return catalogueService.createCatalogue(request);
    }

    /**
     * Cập nhật danh mục
     */
    @PutMapping("/{id}")
    public CatalogueResponse updateCatalogue(
            @PathVariable Long id,
            @Valid @RequestBody CatalogueRequest request) {

        return catalogueService.updateCatalogue(id, request);
    }

    /**
     * Xóa danh mục
     */
    @DeleteMapping("/{id}")
    public void deleteCatalogue(
            @PathVariable Long id) {

        catalogueService.deleteCatalogue(id);
    }
}