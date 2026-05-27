package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.cart.AddToCartRequest;
import com.peacefulpagessanctuary.dto.request.cart.UpdateCartItemRequest;
import com.peacefulpagessanctuary.dto.response.cart.CartResponse;

public interface CartService {

    CartResponse getCartByCustomer(Long customerId);

    CartResponse addToCart(Long customerId, AddToCartRequest request);

    CartResponse updateCartItem(Long cartItemId, UpdateCartItemRequest request);

    void removeCartItem(Long cartItemId);

    void clearCart(Long customerId);
}