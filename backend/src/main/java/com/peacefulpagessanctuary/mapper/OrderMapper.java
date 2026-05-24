package com.peacefulpagessanctuary.mapper;
import com.peacefulpagessanctuary.dto.response.order.OrderDetailResponse;
import com.peacefulpagessanctuary.dto.response.order.OrderResponse;
import com.peacefulpagessanctuary.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final OrderDetailMapper orderDetailMapper;

    public OrderMapper(
            OrderDetailMapper orderDetailMapper
    ) {
        this.orderDetailMapper = orderDetailMapper;
    }

    public OrderResponse toResponse(Order order) {

        List<OrderDetailResponse> orderDetails =
                order.getOrderDetails()
                        .stream()
                        .map(orderDetailMapper::toResponse)
                        .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .customerName(
                        order.getCustomer().getName()
                )
                .status(order.getStatus())
                .total(order.getTotal())
                .shippingFee(order.getShippingFee())

                .couponCode(
                        order.getCoupon() != null
                                ? order.getCoupon().getCode()
                                : null
                )

                .shippingCouponCode(
                        order.getCouponShip() != null
                                ? order.getCouponShip().getCode()
                                : null
                )

                .orderDetails(orderDetails)
                .build();
    }
}