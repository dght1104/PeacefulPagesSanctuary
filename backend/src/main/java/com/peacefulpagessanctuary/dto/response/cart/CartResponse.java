package com.peacefulpagessanctuary.dto.response.cart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {

    private List<CartItemResponse> items;

    private Integer totalItems;

    private Double totalPrice;
}