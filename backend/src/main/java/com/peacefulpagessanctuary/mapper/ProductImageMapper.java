package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.response.product.ProductImageResponse;
import com.peacefulpagessanctuary.model.ProductImage;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    public ProductImageResponse toResponse(
            ProductImage image
    ) {

        return ProductImageResponse.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .isPrimary(image.getIsPrimary())
                .build();
    }
}