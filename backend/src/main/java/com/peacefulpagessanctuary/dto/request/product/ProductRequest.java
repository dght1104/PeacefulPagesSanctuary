package com.peacefulpagessanctuary.dto.request.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String name;

    private Integer received;

    private BigDecimal price;

    private BigDecimal discount;

    private Long catalogueId;

    private Long supplierId;

    private String description;

    private List<ProductImageRequest> images;
}