package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.supplier.SupplierRequest;
import com.peacefulpagessanctuary.dto.response.supplier.SupplierResponse;
import com.peacefulpagessanctuary.service.SupplierService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public List<SupplierResponse> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierResponse getSupplierById(
            @PathVariable Long id
    ) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public SupplierResponse createSupplier(
            @RequestBody SupplierRequest request
    ) {
        return supplierService.createSupplier(request);
    }

    @PutMapping("/{id}")
    public SupplierResponse updateSupplier(
            @PathVariable Long id,
            @RequestBody SupplierRequest request
    ) {
        return supplierService.updateSupplier(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(
            @PathVariable Long id
    ) {
        supplierService.deleteSupplier(id);
    }
}