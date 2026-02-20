package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.entity.Admin;
import com.peacefulpagessanctuary.exception.AccessDeniedException;
import com.peacefulpagessanctuary.exception.InvalidOperationException;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;
import com.peacefulpagessanctuary.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository,
                        BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin register(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new InvalidOperationException("Username already exists");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Admin login(String username, String rawPassword) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        if (!passwordEncoder.matches(rawPassword, admin.getPassword())) {
            throw new AccessDeniedException("Invalid credentials");
        }

        return admin;
    }
}