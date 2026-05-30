package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.product.ProductRequest;
import com.peacefulpagessanctuary.dto.request.product.UpdateProductRequest;
import com.peacefulpagessanctuary.dto.response.product.ProductResponse;
import com.peacefulpagessanctuary.dto.response.product.ProductSummaryResponse;
import com.peacefulpagessanctuary.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductSummaryResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Long id
    ) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(
            @RequestBody ProductRequest request
    ) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request
    ) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<ProductSummaryResponse> searchProducts(
            @RequestParam String keyword
    ) {
        return productService.searchProducts(keyword);
    }

    @GetMapping("/catalogue/{catalogueId}")
    public List<ProductSummaryResponse> getProductsByCatalogue(
            @PathVariable Long catalogueId
    ) {
        return productService.getProductsByCatalogue(catalogueId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<ProductSummaryResponse> getProductsBySupplier(
            @PathVariable Long supplierId
    ) {
        return productService.getProductsBySupplier(supplierId);
    }
}