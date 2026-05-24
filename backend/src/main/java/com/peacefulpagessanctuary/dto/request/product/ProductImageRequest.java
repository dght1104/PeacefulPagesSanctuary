package com.peacefulpagessanctuary.dto.request.product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageRequest {

    private String imageUrl;

    private Boolean isPrimary;
}