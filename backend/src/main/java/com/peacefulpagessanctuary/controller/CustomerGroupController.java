package com.peacefulpagessanctuary.controller;


import com.peacefulpagessanctuary.dto.request.customergroup.CustomerGroupRequest;
import com.peacefulpagessanctuary.dto.response.customergroup.CustomerGroupResponse;
import com.peacefulpagessanctuary.service.CustomerGroupService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-groups")
@RequiredArgsConstructor
public class CustomerGroupController {

    private final CustomerGroupService customerGroupService;

    @GetMapping
    public ResponseEntity<List<CustomerGroupResponse>> getAllCustomerGroups() {

        return ResponseEntity.ok(
                customerGroupService.getAllCustomerGroups()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerGroupResponse> getCustomerGroupById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                customerGroupService.getCustomerGroupById(id)
        );
    }

    @PostMapping
    public ResponseEntity<CustomerGroupResponse> createCustomerGroup(
            @RequestBody CustomerGroupRequest request
    ) {

        return ResponseEntity.ok(
                customerGroupService.createCustomerGroup(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerGroupResponse> updateCustomerGroup(
            @PathVariable Long id,
            @RequestBody CustomerGroupRequest request
    ) {

        return ResponseEntity.ok(
                customerGroupService.updateCustomerGroup(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerGroup(
            @PathVariable Long id
    ) {

        customerGroupService.deleteCustomerGroup(id);

        return ResponseEntity.noContent().build();
    }
}