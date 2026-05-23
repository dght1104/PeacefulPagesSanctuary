package com.peacefulpagessanctuary.dto.response.cart;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {

    private Long cartItemId;

    private Long productId;

    private String productName;

    private String productImage;

    private Double price;

    private Integer quantity;

    private Double subtotal;
}