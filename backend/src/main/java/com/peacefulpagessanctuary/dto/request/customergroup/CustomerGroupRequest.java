package com.peacefulpagessanctuary.dto.request.customergroup;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerGroupRequest {

    private String description;

    private BigDecimal minPurchase;
}