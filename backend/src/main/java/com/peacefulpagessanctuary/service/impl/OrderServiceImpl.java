package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.order.CheckoutRequest;
import com.peacefulpagessanctuary.dto.request.order.UpdateOrderStatusRequest;
import com.peacefulpagessanctuary.dto.response.order.OrderResponse;
import com.peacefulpagessanctuary.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public List<OrderResponse> getAllOrders() {
        return null;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderResponse> getOrdersByCustomer(Long customerId) {
        return null;
    }

    @Override
    public OrderResponse checkout(Long customerId, CheckoutRequest request) {
        return null;
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}