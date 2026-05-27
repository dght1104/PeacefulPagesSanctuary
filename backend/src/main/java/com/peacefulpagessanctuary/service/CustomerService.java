package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.customer.UpdateProfileRequest;
import com.peacefulpagessanctuary.dto.response.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateProfile(Long id, UpdateProfileRequest request);

    void deleteCustomer(Long id);
}