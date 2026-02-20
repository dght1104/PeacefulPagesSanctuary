package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.entity.Product;
import com.peacefulpagessanctuary.payload.ApiResponse;
import com.peacefulpagessanctuary.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Product>>> getAllProducts(Pageable pageable) {
        Page<Product> page = productService.getAllProducts(pageable);
        return ResponseEntity.ok(ApiResponse.success("Products fetched successfully", page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Product fetched successfully", product));
    }
}