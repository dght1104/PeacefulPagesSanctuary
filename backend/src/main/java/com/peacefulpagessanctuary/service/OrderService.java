package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.order.CheckoutRequest;
import com.peacefulpagessanctuary.dto.request.order.UpdateOrderStatusRequest;
import com.peacefulpagessanctuary.dto.response.order.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrdersByCustomer(Long customerId);

    OrderResponse checkout(Long customerId, CheckoutRequest request);

    OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

    void cancelOrder(Long orderId);
}