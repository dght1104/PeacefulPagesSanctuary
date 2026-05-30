package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.auth.RegisterRequest;
import com.peacefulpagessanctuary.dto.request.customer.CustomerRequest;
import com.peacefulpagessanctuary.dto.request.customer.UpdateProfileRequest;
import com.peacefulpagessanctuary.dto.response.customer.CustomerResponse;
import com.peacefulpagessanctuary.model.Customer;
import com.peacefulpagessanctuary.model.CustomerGroup;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    // Dùng cho Register
    public Customer toEntity(RegisterRequest request) {

        return Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(request.getPassword())
                .verified(false)
                .active(true)
                .build();
    }

    // Dùng cho Admin Dashboard Create Customer
    public Customer toEntity(
            CustomerRequest request,
            CustomerGroup customerGroup
    ) {

        return Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(request.getPassword())
                .image(request.getImage())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .verified(request.getVerified())
                .active(request.getActive())
                .customerGroup(customerGroup)
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {

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

    // Admin update customer
    public void updateEntity(
            Customer customer,
            CustomerRequest request,
            CustomerGroup customerGroup
    ) {

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setUsername(request.getUsername());
        customer.setImage(request.getImage());
        customer.setAddress(request.getAddress());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setVerified(request.getVerified());
        customer.setActive(request.getActive());
        customer.setCustomerGroup(customerGroup);

        if (request.getPassword() != null
                && !request.getPassword().isBlank()) {

            customer.setPassword(request.getPassword());
        }
    }

    // Customer tự sửa profile
    public void updateProfile(
            Customer customer,
            UpdateProfileRequest request
    ) {

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setImage(request.getImage());
        customer.setAddress(request.getAddress());
        customer.setDateOfBirth(request.getDateOfBirth());
    }
}