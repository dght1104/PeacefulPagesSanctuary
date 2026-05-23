package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {

    List<CustomerGroup> findByDescriptionContaining(String keyword);

    List<CustomerGroup> findByMinPurchaseGreaterThan(BigDecimal amount);

    List<CustomerGroup> findByMinPurchaseLessThanEqual(BigDecimal amount);

}