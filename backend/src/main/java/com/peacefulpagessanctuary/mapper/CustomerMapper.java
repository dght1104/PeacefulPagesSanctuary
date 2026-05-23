package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.auth.RegisterRequest;
import com.peacefulpagessanctuary.dto.response.customer.CustomerResponse;
import com.peacefulpagessanctuary.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    /**
     * Convert RegisterRequest -> Customer Entity
     */
    public Customer toEntity(RegisterRequest request) {
        if (request == null) {
            return null;
        }

        return Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                // Password sẽ được encode trong service trước hoặc sau khi gọi mapper
                .password(request.getPassword())

                .verified(false)
                .active(true)
                .build();
    }

    /**
     * Convert Customer Entity -> CustomerResponse DTO
     */
    public CustomerResponse toResponse(Customer customer) {
        if (customer == null) {
            return null;
        }

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .username(customer.getUsername())
                .image(customer.getImage())
                .address(customer.getAddress())
                .dateOfBirth(customer.getDateOfBirth())
                .customerGroupName(
                        customer.getCustomerGroup() != null
                                ? customer.getCustomerGroup().getDescription()
                                : null
                )
                .verified(customer.getVerified())
                .active(customer.getActive())
                .build();
    }

    /**
     * Update entity từ RegisterRequest (nếu muốn tái sử dụng cho update profile)
     */
    public void updateEntity(Customer customer, RegisterRequest request) {
        if (customer == null || request == null) {
            return;
        }

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setUsername(request.getUsername());


        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            customer.setPassword(request.getPassword());
        }
    }
}