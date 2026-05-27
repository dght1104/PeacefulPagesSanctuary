package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.admin.AdminRequest;
import com.peacefulpagessanctuary.dto.response.admin.AdminResponse;

import java.util.List;

public interface AdminService {

    List<AdminResponse> getAllAdmins();

    AdminResponse getAdminById(Long id);

    AdminResponse createAdmin(AdminRequest request);

    AdminResponse updateAdmin(Long id, AdminRequest request);

    void deleteAdmin(Long id);
}