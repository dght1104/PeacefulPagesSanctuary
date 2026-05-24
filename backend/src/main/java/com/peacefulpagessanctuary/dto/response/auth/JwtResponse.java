package com.peacefulpagessanctuary.dto.response.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String accessToken;

    private String refreshToken;

    @Builder.Default
    private String tokenType = "Bearer";

    private Long customerId;

    private String name;

    private String email;

    private String username;

    private String customerGroup;
}