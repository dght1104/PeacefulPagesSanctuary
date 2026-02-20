package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.entity.CartItem;
import com.peacefulpagessanctuary.entity.Customer;
import com.peacefulpagessanctuary.payload.ApiResponse;
import com.peacefulpagessanctuary.service.CartService;
import com.peacefulpagessanctuary.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CustomerService customerService;

    public CartController(CartService cartService,
                          CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItem>> addToCart(@RequestParam String email,
                                                           @RequestParam Long productId,
                                                           @RequestParam int quantity) {

        Customer customer = customerService.getByEmail(email);
        CartItem item = cartService.addToCart(customer, productId, quantity);

        return ResponseEntity.ok(ApiResponse.success("Added to cart", item));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItem>>> getCart(@RequestParam String email) {

        Customer customer = customerService.getByEmail(email);
        List<CartItem> items = cartService.getCart(customer);

        return ResponseEntity.ok(ApiResponse.success("Cart fetched", items));
    }
}