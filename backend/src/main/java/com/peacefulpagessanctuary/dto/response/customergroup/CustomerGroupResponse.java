package com.peacefulpagessanctuary.dto.response.customergroup;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerGroupResponse {

    private Long groupId;

    private String description;

    private BigDecimal minPurchase;
}