package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.entity.Admin;
import com.peacefulpagessanctuary.entity.Customer;
import com.peacefulpagessanctuary.payload.ApiResponse;
import com.peacefulpagessanctuary.service.AdminService;
import com.peacefulpagessanctuary.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerService customerService;
    private final AdminService adminService;

    public AuthController(CustomerService customerService,
                          AdminService adminService) {
        this.customerService = customerService;
        this.adminService = adminService;
    }

    @PostMapping("/customer/register")
    public ResponseEntity<ApiResponse<Customer>> registerCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.register(customer);
        return ResponseEntity.ok(ApiResponse.success("Customer registered successfully", saved));
    }

    @PostMapping("/customer/login")
    public ResponseEntity<ApiResponse<Customer>> loginCustomer(@RequestParam String email,
                                                               @RequestParam String password) {
        Customer customer = customerService.login(email, password);
        return ResponseEntity.ok(ApiResponse.success("Login successful", customer));
    }

    @PostMapping("/admin/register")
    public ResponseEntity<ApiResponse<Admin>> registerAdmin(@RequestBody Admin admin) {
        Admin saved = adminService.register(admin);
        return ResponseEntity.ok(ApiResponse.success("Admin registered successfully", saved));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ApiResponse<Admin>> loginAdmin(@RequestParam String username,
                                                         @RequestParam String password) {
        Admin admin = adminService.login(username, password);
        return ResponseEntity.ok(ApiResponse.success("Login successful", admin));
    }
}