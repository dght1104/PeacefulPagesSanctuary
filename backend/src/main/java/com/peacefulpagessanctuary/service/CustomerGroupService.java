package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.customergroup.CustomerGroupRequest;
import com.peacefulpagessanctuary.dto.response.customergroup.CustomerGroupResponse;

import java.util.List;

public interface CustomerGroupService {

    List<CustomerGroupResponse> getAllCustomerGroups();

    CustomerGroupResponse getCustomerGroupById(Long id);

    CustomerGroupResponse createCustomerGroup(CustomerGroupRequest request);

    CustomerGroupResponse updateCustomerGroup(Long id, CustomerGroupRequest request);

    void deleteCustomerGroup(Long id);
}