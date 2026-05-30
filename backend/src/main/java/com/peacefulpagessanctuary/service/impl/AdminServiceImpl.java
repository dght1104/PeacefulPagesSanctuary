package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.admin.AdminRequest;
import com.peacefulpagessanctuary.dto.response.admin.AdminResponse;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;
import com.peacefulpagessanctuary.mapper.AdminMapper;
import com.peacefulpagessanctuary.model.Admin;
import com.peacefulpagessanctuary.model.RoleAdmin;
import com.peacefulpagessanctuary.repository.AdminRepository;
import com.peacefulpagessanctuary.repository.RoleAdminRepository;
import com.peacefulpagessanctuary.service.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleAdminRepository roleAdminRepository;
    private final AdminMapper adminMapper;

    @Override
    public List<AdminResponse> getAllAdmins() {

        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toResponse)
                .toList();
    }

    @Override
    public AdminResponse getAdminById(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found"));

        return adminMapper.toResponse(admin);
    }

    @Override
    public AdminResponse createAdmin(AdminRequest request) {

        RoleAdmin role = roleAdminRepository.findById(
                request.getRoleId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Role not found"));

        Admin admin = adminMapper.toEntity(request, role);

        Admin savedAdmin = adminRepository.save(admin);

        return adminMapper.toResponse(savedAdmin);
    }

    @Override
    public AdminResponse updateAdmin(Long id,
                                     AdminRequest request) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found"));

        RoleAdmin role = roleAdminRepository.findById(
                request.getRoleId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Role not found"));

        adminMapper.updateEntity(admin, request, role);

        Admin updatedAdmin = adminRepository.save(admin);

        return adminMapper.toResponse(updatedAdmin);
    }

    @Override
    public void deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found"));

        adminRepository.delete(admin);
    }
}