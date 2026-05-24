package com.peacefulpagessanctuary.dto.request.order;

import com.peacefulpagessanctuary.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderStatusRequest {

    private OrderStatus status;
}