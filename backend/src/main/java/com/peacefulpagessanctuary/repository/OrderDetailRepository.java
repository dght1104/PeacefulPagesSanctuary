package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.model.Order;
import com.peacefulpagessanctuary.model.OrderDetail;
import com.peacefulpagessanctuary.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrder(Order order);

    List<OrderDetail> findByProduct(Product product);

    List<OrderDetail> findByPriceGreaterThan(BigDecimal price);

    List<OrderDetail> findByQuantityGreaterThan(Integer quantity);

}