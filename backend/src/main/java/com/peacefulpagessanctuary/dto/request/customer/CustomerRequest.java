package com.peacefulpagessanctuary.dto.request.customer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    private String name;

    private String email;

    private String phone;

    private String username;

    private String password;

    private String image;

    private String address;

    private LocalDate dateOfBirth;

    private Boolean active;

    private Boolean verified;

    private Long customerGroupId;
}