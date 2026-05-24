package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.admin.AdminRequest;
import com.peacefulpagessanctuary.dto.response.admin.AdminResponse;
import com.peacefulpagessanctuary.model.Admin;
import com.peacefulpagessanctuary.model.RoleAdmin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminRequest request, RoleAdmin role) {
        return Admin.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(role)
                .build();
    }

    public AdminResponse toResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .username(admin.getUsername())
                .roleName(
                        admin.getRole() != null
                                ? admin.getRole().getRoleName()
                                : null
                )
                .build();
    }

    public void updateEntity(
            Admin admin,
            AdminRequest request,
            RoleAdmin role
    ) {

        admin.setName(request.getName());
        admin.setUsername(request.getUsername());

        if(request.getPassword() != null
                && !request.getPassword().isBlank()) {

            admin.setPassword(request.getPassword());
        }

        admin.setRole(role);
    }
}