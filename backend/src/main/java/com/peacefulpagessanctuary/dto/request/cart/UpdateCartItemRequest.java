package com.peacefulpagessanctuary.dto.request.cart;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCartItemRequest {

    private Integer quantity;
}