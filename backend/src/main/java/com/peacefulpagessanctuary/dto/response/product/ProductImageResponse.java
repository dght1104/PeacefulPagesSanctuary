package com.peacefulpagessanctuary.dto.response.product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageResponse {

    private Long id;

    private String imageUrl;

    private Boolean isPrimary;
}