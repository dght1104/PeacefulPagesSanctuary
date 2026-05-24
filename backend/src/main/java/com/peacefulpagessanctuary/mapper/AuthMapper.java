package com.peacefulpagessanctuary.mapper;

import com.peacefulpagessanctuary.dto.request.auth.RegisterRequest;
import com.peacefulpagessanctuary.dto.response.auth.JwtResponse;
import com.peacefulpagessanctuary.model.Customer;
import com.peacefulpagessanctuary.model.CustomerGroup;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public Customer toCustomer(
            RegisterRequest request,
            String encodedPassword,
            CustomerGroup customerGroup
    ) {

        return Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(encodedPassword)
                .customerGroup(customerGroup)
                .verified(false)
                .active(true)
                .build();
    }

    public JwtResponse toJwtResponse(
            Customer customer,
            String accessToken,
            String refreshToken
    ) {

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .customerId(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .customerGroup(
                        customer.getCustomerGroup() != null
                                ? customer.getCustomerGroup().getDescription()
                                : null
                )
                .build();
    }
}