package com.peacefulpagessanctuary.repository;

import com.peacefulpagessanctuary.enums.OrderStatus;
import com.peacefulpagessanctuary.model.Customer;
import com.peacefulpagessanctuary.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByCustomerAndStatus(
        Customer customer,
        OrderStatus status
    );

    List<Order> findByOrderDateBetween(
        LocalDateTime startDate,
        LocalDateTime endDate
    );

    List<Order> findByTotalGreaterThanEqual(Double total);

}