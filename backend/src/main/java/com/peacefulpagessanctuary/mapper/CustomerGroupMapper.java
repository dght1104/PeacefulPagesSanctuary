package com.peacefulpagessanctuary.mapper;
import com.peacefulpagessanctuary.dto.request.customergroup.CustomerGroupRequest;
import com.peacefulpagessanctuary.dto.response.customergroup.CustomerGroupResponse;
import com.peacefulpagessanctuary.model.CustomerGroup;
import org.springframework.stereotype.Component;

@Component
public class CustomerGroupMapper {

    public CustomerGroup toEntity(
            CustomerGroupRequest request
    ) {

        return CustomerGroup.builder()
                .description(request.getDescription())
                .minPurchase(request.getMinPurchase())
                .build();
    }

    public CustomerGroupResponse toResponse(
            CustomerGroup customerGroup
    ) {

        return CustomerGroupResponse.builder()
                .groupId(customerGroup.getGroupId())
                .description(customerGroup.getDescription())
                .minPurchase(customerGroup.getMinPurchase())
                .build();
    }

    public void updateEntity(
            CustomerGroup customerGroup,
            CustomerGroupRequest request
    ) {

        customerGroup.setDescription(
                request.getDescription()
        );

        customerGroup.setMinPurchase(
                request.getMinPurchase()
        );
    }
}