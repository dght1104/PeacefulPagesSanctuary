package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.entity.Order;
import com.peacefulpagessanctuary.payload.ApiResponse;
import com.peacefulpagessanctuary.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse<Order>> checkout(
            @RequestParam String email,
            @RequestParam(required = false) String productCouponCode,
            @RequestParam(required = false) String shippingCouponCode,
            @RequestParam long shippingFee
    ) {

        Order order = orderService.checkout(
                email,
                productCouponCode,
                shippingCouponCode,
                shippingFee
        );

        return ResponseEntity.ok(
                ApiResponse.success("Checkout successful", order)
        );
    }
}