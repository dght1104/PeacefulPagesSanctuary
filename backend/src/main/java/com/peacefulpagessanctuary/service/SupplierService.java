package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.supplier.SupplierRequest;
import com.peacefulpagessanctuary.dto.response.supplier.SupplierResponse;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse getSupplierById(Long id);

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    void deleteSupplier(Long id);
}