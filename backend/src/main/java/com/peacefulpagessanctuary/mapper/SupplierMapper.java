package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.supplier.SupplierRequest;
import com.peacefulpagessanctuary.dto.response.supplier.SupplierResponse;
import com.peacefulpagessanctuary.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(
            SupplierRequest request
    ) {

        return Supplier.builder()
                .name(request.getName())
                .build();
    }

    public SupplierResponse toResponse(
            Supplier supplier
    ) {

        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .build();
    }

    public void updateEntity(
            Supplier supplier,
            SupplierRequest request
    ) {

        supplier.setName(request.getName());
    }
}