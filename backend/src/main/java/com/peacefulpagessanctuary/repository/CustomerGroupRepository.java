package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.entity.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {
}