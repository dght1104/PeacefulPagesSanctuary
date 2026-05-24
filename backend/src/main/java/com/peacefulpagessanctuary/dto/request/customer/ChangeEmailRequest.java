package com.peacefulpagessanctuary.dto.request.customer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeEmailRequest {

    private String newEmail;

    private String password;
}