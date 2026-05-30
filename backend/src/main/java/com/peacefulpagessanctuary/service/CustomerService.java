package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.customer.CustomerRequest;
import com.peacefulpagessanctuary.dto.request.customer.UpdateProfileRequest;
import com.peacefulpagessanctuary.dto.response.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {

  List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request
    );

    CustomerResponse updateProfile(
            Long id,
            UpdateProfileRequest request
    );

    void deleteCustomer(Long id);
}