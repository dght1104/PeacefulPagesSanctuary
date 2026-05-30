package com.peacefulpagessanctuary.controller;
 import com.peacefulpagessanctuary.dto.request.cart.AddToCartRequest;
import com.peacefulpagessanctuary.dto.request.cart.UpdateCartItemRequest;
import com.peacefulpagessanctuary.dto.response.cart.CartResponse;
import com.peacefulpagessanctuary.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Xem giỏ hàng của khách hàng
     */
    @GetMapping("/{customerId}")
    public CartResponse getCartByCustomer(
            @PathVariable Long customerId) {

        return cartService.getCartByCustomer(customerId);
    }

    /**
     * Thêm sản phẩm vào giỏ hàng
     */
    @PostMapping("/{customerId}/add")
    public CartResponse addToCart(
            @PathVariable Long customerId,
            @RequestBody AddToCartRequest request) {

        return cartService.addToCart(customerId, request);
    }

    /**
     * Cập nhật số lượng sản phẩm trong giỏ
     */
    @PutMapping("/item/{cartItemId}")
    public CartResponse updateCartItem(
            @PathVariable Long cartItemId,
            @RequestBody UpdateCartItemRequest request) {

        return cartService.updateCartItem(cartItemId, request);
    }

    /**
     * Xóa 1 sản phẩm khỏi giỏ hàng
     */
    @DeleteMapping("/item/{cartItemId}")
    public void removeCartItem(
            @PathVariable Long cartItemId) {

        cartService.removeCartItem(cartItemId);
    }

    /**
     * Xóa toàn bộ giỏ hàng
     */
    @DeleteMapping("/clear/{customerId}")
    public void clearCart(
            @PathVariable Long customerId) {

        cartService.clearCart(customerId);
    }
}