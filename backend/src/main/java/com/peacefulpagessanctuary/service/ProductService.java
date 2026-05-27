package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.product.ProductRequest;
import com.peacefulpagessanctuary.dto.request.product.UpdateProductRequest;
import com.peacefulpagessanctuary.dto.response.product.ProductResponse;
import com.peacefulpagessanctuary.dto.response.product.ProductSummaryResponse;

import java.util.List;

public interface ProductService {

    List<ProductSummaryResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Long id, UpdateProductRequest request);

    void deleteProduct(Long id);

    List<ProductSummaryResponse> searchProducts(String keyword);

    List<ProductSummaryResponse> getProductsByCatalogue(Long catalogueId);

    List<ProductSummaryResponse> getProductsBySupplier(Long supplierId);
}