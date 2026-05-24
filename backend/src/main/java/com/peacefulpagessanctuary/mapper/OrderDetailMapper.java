package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.response.order.OrderDetailResponse;
import com.peacefulpagessanctuary.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderDetailMapper {

    public OrderDetailResponse toResponse(
            OrderDetail orderDetail
    ) {

        BigDecimal subtotal =
                orderDetail.getPrice()
                        .multiply(
                                BigDecimal.valueOf(
                                        orderDetail.getQuantity()
                                )
                        );

        return OrderDetailResponse.builder()
                .productId(
                        orderDetail.getProduct().getId()
                )
                .productName(
                        orderDetail.getProduct().getName()
                )
                .productImage(
                        orderDetail.getProduct().getImages() != null
                                && !orderDetail.getProduct()
                                        .getImages().isEmpty()
                        ? orderDetail.getProduct()
                                .getImages()
                                .get(0)
                                .getImageUrl()
                        : null
                )
                .price(orderDetail.getPrice())
                .quantity(orderDetail.getQuantity())
                .subtotal(subtotal)
                .build();
    }
}