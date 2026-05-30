package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.product.ProductRequest;
import com.peacefulpagessanctuary.dto.request.product.UpdateProductRequest;
import com.peacefulpagessanctuary.dto.response.product.ProductResponse;
import com.peacefulpagessanctuary.dto.response.product.ProductSummaryResponse;
import com.peacefulpagessanctuary.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductSummaryResponse> getAllProducts() {
        return null;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<ProductSummaryResponse> searchProducts(String keyword) {
        return null;
    }

    @Override
    public List<ProductSummaryResponse> getProductsByCatalogue(Long catalogueId) {
        return null;
    }

    @Override
    public List<ProductSummaryResponse> getProductsBySupplier(Long supplierId) {
        return null;
    }
}