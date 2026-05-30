package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.response.cart.CartItemResponse;
import com.peacefulpagessanctuary.dto.response.cart.CartResponse;
import com.peacefulpagessanctuary.model.CartItem;

import org.springframework.stereotype.Component;

import java.util.List;

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

    public CartResponse toCartResponse(
            List<CartItem> cartItems) {

        List<CartItemResponse> items = cartItems.stream()
                .map(this::toResponse)
                .toList();

        double totalPrice = items.stream()
                .mapToDouble(CartItemResponse::getSubtotal)
                .sum();

        int totalItems = items.stream()
                .mapToInt(CartItemResponse::getQuantity)
                .sum();

        return CartResponse.builder()
                .items(items)
                .totalItems(totalItems)
                .totalPrice(totalPrice)
                .build();
    }
}