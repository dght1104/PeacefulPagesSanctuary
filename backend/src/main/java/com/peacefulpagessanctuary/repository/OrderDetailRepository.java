package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}