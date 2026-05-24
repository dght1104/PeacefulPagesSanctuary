package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.response.cart.CartItemResponse;
import com.peacefulpagessanctuary.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemResponse toResponse(CartItem cartItem) {

        double price = cartItem.getProduct()
                .getPrice()
                .doubleValue();

        int quantity = cartItem.getQuantity();

        return CartItemResponse.builder()
                .cartItemId(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .price(price)
                .quantity(quantity)
                .subtotal(price * quantity)
                .build();
    }
}