package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.admin.AdminRequest;
import com.peacefulpagessanctuary.dto.response.admin.AdminResponse;
import com.peacefulpagessanctuary.service.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Lấy danh sách tất cả Admin
     */
    @GetMapping
    public List<AdminResponse> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    /**
     * Lấy Admin theo ID
     */
    @GetMapping("/{id}")
    public AdminResponse getAdminById(
            @PathVariable Long id) {

        return adminService.getAdminById(id);
    }

    /**
     * Tạo Admin mới
     */
    @PostMapping
    public AdminResponse createAdmin(
            @RequestBody AdminRequest request) {

        return adminService.createAdmin(request);
    }

    /**
     * Cập nhật Admin
     */
    @PutMapping("/{id}")
    public AdminResponse updateAdmin(
            @PathVariable Long id,
            @RequestBody AdminRequest request) {

        return adminService.updateAdmin(id, request);
    }

    /**
     * Xóa Admin
     */
    @DeleteMapping("/{id}")
    public void deleteAdmin(
            @PathVariable Long id) {

        adminService.deleteAdmin(id);
    }
}