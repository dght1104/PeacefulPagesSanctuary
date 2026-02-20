package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.entity.Product;
import com.peacefulpagessanctuary.payload.ApiResponse;
import com.peacefulpagessanctuary.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products")
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {

        Product saved = productRepository.save(product);

        return ResponseEntity.ok(
                ApiResponse.success("Product created successfully", saved)
        );
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id,
                                                              @RequestBody Product updated) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setProdReceived(updated.getProdReceived());
        product.setDiscountPercent(updated.getDiscountPercent());

        Product saved = productRepository.save(product);

        return ResponseEntity.ok(
                ApiResponse.success("Product updated successfully", saved)
        );
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@PathVariable Long id) {

        productRepository.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.success("Product deleted successfully", null)
        );
    }
}