package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.order.CheckoutRequest;
import com.peacefulpagessanctuary.dto.request.order.UpdateOrderStatusRequest;
import com.peacefulpagessanctuary.dto.response.order.OrderResponse;
import com.peacefulpagessanctuary.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(
            @PathVariable Long id
    ) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderResponse> getOrdersByCustomer(
            @PathVariable Long customerId
    ) {
        return orderService.getOrdersByCustomer(customerId);
    }

    @PostMapping("/checkout/{customerId}")
    public OrderResponse checkout(
            @PathVariable Long customerId,
            @RequestBody CheckoutRequest request
    ) {
        return orderService.checkout(customerId, request);
    }

    @PutMapping("/{orderId}/status")
    public OrderResponse updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request
    ) {
        return orderService.updateOrderStatus(
                orderId,
                request
        );
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(
            @PathVariable Long orderId
    ) {
        orderService.cancelOrder(orderId);
    }
}