package com.peacefulpagessanctuary.dto.response.product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSummaryResponse {

    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;

    private String thumbnail;

    private String catalogueName;
}