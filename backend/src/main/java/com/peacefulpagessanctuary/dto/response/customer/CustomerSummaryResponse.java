package com.peacefulpagessanctuary.dto.response.customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSummaryResponse {

    private Long id;

    private String name;

    private String email;

    private Boolean verified;

    private Boolean active;
}