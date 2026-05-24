package com.peacefulpagessanctuary.dto.response.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponse {

    private Long productId;

    private String productName;

    private String productImage;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal subtotal;
}