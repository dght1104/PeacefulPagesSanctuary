package com.peacefulpagessanctuary.dto.response.auth;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String image;
    private String address;
    private LocalDate dateOfBirth;
    private String customerGroupName;
    private Boolean verified;
    private Boolean active;
}