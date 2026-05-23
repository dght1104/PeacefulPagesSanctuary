package com.peacefulpagessanctuary.dto.request.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}