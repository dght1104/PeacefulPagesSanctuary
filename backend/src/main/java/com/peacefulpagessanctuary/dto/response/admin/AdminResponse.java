package com.peacefulpagessanctuary.dto.response.admin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponse {

    private Long id;

    private String name;

    private String username;

    private String roleName;
}