package com.peacefulpagessanctuary.dto.response.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Integer received;

    private Integer sold;

    private BigDecimal price;

    private BigDecimal discount;

    private String catalogueName;

    private String supplierName;

    private String description;

    private List<ProductImageResponse> images;
}