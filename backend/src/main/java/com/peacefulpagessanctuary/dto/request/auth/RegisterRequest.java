package com.peacefulpagessanctuary.dto.request.auth;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String address;
    private LocalDate dateOfBirth;
}