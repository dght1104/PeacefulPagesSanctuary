package com.peacefulpagessanctuary.service.impl;


import com.peacefulpagessanctuary.dto.request.supplier.SupplierRequest;
import com.peacefulpagessanctuary.dto.response.supplier.SupplierResponse;

import com.peacefulpagessanctuary.exception.ResourceNotFoundException;

import com.peacefulpagessanctuary.mapper.SupplierMapper;

import com.peacefulpagessanctuary.model.Supplier;

import com.peacefulpagessanctuary.repository.SupplierRepository;

import com.peacefulpagessanctuary.service.SupplierService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public List<SupplierResponse> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toResponse)
                .toList();
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        return supplierMapper.toResponse(supplier);
    }

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {

        Supplier supplier = supplierMapper.toEntity(request);

        Supplier savedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toResponse(savedSupplier);
    }

    @Override
    public SupplierResponse updateSupplier(
            Long id,
            SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        supplierMapper.updateEntity(supplier, request);

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toResponse(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier not found"));

        supplierRepository.delete(supplier);
    }
}