package com.peacefulpagessanctuary.dto.request.customer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordRequest {

    private String token;

    private String newPassword;

    private String confirmPassword;
}