package com.peacefulpagessanctuary.dto.request.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequest {

    private String name;

    private String username;

    private String password;

    private Long roleId;
}