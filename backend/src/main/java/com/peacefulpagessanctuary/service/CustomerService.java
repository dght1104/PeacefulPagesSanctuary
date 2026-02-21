package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.entity.Customer;
import com.peacefulpagessanctuary.entity.CustomerGroup;
import com.peacefulpagessanctuary.exception.AccessDeniedException;
import com.peacefulpagessanctuary.exception.InvalidOperationException;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;
import com.peacefulpagessanctuary.repository.CustomerGroupRepository;
import com.peacefulpagessanctuary.repository.CustomerRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerGroupRepository customerGroupRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerGroupRepository customerGroupRepository,
                           PasswordEncoder  passwordEncoder) {
        this.customerRepository = customerRepository;
        this.customerGroupRepository = customerGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer register(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new InvalidOperationException("Email already exists");
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setTotalSpent(BigDecimal.ZERO);

        CustomerGroup defaultGroup = customerGroupRepository.findByDescription("Slivers")
        .orElseThrow(() -> 
            new ResourceNotFoundException("Default group not found"));
        customer.setCustomerGroup(defaultGroup);

        return customerRepository.save(customer);
    }

    public Customer login(String email, String rawPassword) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        if (!passwordEncoder.matches(rawPassword, customer.getPassword())) {
            throw new AccessDeniedException("Invalid credentials");
        }

        return customer;
    }

    public Customer getByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
}