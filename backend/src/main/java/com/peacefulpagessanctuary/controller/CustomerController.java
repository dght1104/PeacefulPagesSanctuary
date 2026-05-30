package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.customer.CustomerRequest;
import com.peacefulpagessanctuary.dto.request.customer.UpdateProfileRequest;
import com.peacefulpagessanctuary.dto.response.customer.CustomerResponse;
import com.peacefulpagessanctuary.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

        return ResponseEntity.ok(
                customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateProfile(
            @PathVariable Long id,
            @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.ok(
                customerService.updateProfile(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(
                customerService.createCustomer(request));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(
                customerService.updateCustomer(id, request));
    }
}