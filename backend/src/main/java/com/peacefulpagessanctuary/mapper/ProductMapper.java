package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.product.ProductRequest;
import com.peacefulpagessanctuary.dto.request.product.UpdateProductRequest;
import com.peacefulpagessanctuary.dto.response.product.ProductImageResponse;
import com.peacefulpagessanctuary.dto.response.product.ProductResponse;
import com.peacefulpagessanctuary.dto.response.product.ProductSummaryResponse;
import com.peacefulpagessanctuary.model.Catalogue;
import com.peacefulpagessanctuary.model.Product;
import com.peacefulpagessanctuary.model.Supplier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductMapper {

    private final ProductImageMapper productImageMapper;

    public ProductMapper(
            ProductImageMapper productImageMapper) {
        this.productImageMapper = productImageMapper;
    }

    public Product toEntity(
            ProductRequest request,
            Catalogue catalogue,
            Supplier supplier) {

        return Product.builder()
                .name(request.getName())
                .received(request.getReceived())
                .sold(0)
                .price(request.getPrice())
                .discount(
                        request.getDiscount() != null
                                ? request.getDiscount()
                                : BigDecimal.ZERO)
                .catalogue(catalogue)
                .supplier(supplier)
                .description(request.getDescription())
                .build();
    }

    public ProductResponse toResponse(
            Product product) {

        List<ProductImageResponse> images = product.getImages()
                .stream()
                .map(productImageMapper::toResponse)
                .toList();

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .received(product.getReceived())
                .sold(product.getSold())
                .price(product.getPrice())
                .discount(product.getDiscount())

                .catalogueName(
                        product.getCatalogue() != null
                                ? product.getCatalogue().getName()
                                : null)

                .supplierName(
                        product.getSupplier() != null
                                ? product.getSupplier().getName()
                                : null)

                .description(product.getDescription())
                .images(images)
                .build();
    }

    public ProductSummaryResponse toSummaryResponse(
            Product product) {

        return ProductSummaryResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .thumbnail(product.getMainImage())

                .catalogueName(
                        product.getCatalogue() != null
                                ? product.getCatalogue().getName()
                                : null)
                .build();
    }

    public void updateEntity(
            Product product,
            UpdateProductRequest request,
            Catalogue catalogue,
            Supplier supplier) {

        product.setName(request.getName());
        product.setReceived(request.getReceived());
        product.setPrice(request.getPrice());

        product.setDiscount(
                request.getDiscount() != null
                        ? request.getDiscount()
                        : BigDecimal.ZERO);

        product.setCatalogue(catalogue);
        product.setSupplier(supplier);
        product.setDescription(request.getDescription());
    }
}