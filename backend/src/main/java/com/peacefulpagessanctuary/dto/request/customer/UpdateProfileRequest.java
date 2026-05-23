package com.peacefulpagessanctuary.dto.request.customer;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProfileRequest {

    private String name;

    private String phone;

    private String image;

    private String address;

    private LocalDate dateOfBirth;
}