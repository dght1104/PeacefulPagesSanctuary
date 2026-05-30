package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.auth.ChangePasswordRequest;
import com.peacefulpagessanctuary.dto.request.auth.LoginRequest;
import com.peacefulpagessanctuary.dto.request.auth.RefreshTokenRequest;
import com.peacefulpagessanctuary.dto.request.auth.RegisterRequest;
import com.peacefulpagessanctuary.dto.response.auth.JwtResponse;

import com.peacefulpagessanctuary.exception.AccessDeniedException;
import com.peacefulpagessanctuary.exception.InvalidOperationException;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;

import com.peacefulpagessanctuary.model.Customer;

import com.peacefulpagessanctuary.repository.CustomerRepository;

import com.peacefulpagessanctuary.security.JwtUtils;

import com.peacefulpagessanctuary.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse login(LoginRequest request) {

        Customer customer = customerRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        boolean isMatch = passwordEncoder.matches(
                request.getPassword(),
                customer.getPassword());

        if (!isMatch) {
            throw new AccessDeniedException("Invalid credentials");
        }

        String accessToken = jwtUtils.generateToken(
                customer.getUsername());

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(accessToken)
                .customerId(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .build();
    }

    @Override
    public JwtResponse register(RegisterRequest request) {

        boolean exists = customerRepository
                .existsByUsername(request.getUsername());

        if (exists) {
            throw new InvalidOperationException(
                    "Username already exists");
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword()))
                .build();

        customerRepository.save(customer);

        String accessToken = jwtUtils.generateToken(
                customer.getUsername());

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(accessToken)
                .customerId(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .build();
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenRequest request) {

        String refreshToken = request.getRefreshToken();

        if (!jwtUtils.validateToken(refreshToken)) {
            throw new AccessDeniedException(
                    "Invalid refresh token");
        }

        String username = jwtUtils.extractUsername(refreshToken);

        Customer customer = customerRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found"));

        String newAccessToken = jwtUtils.generateToken(username);

        return JwtResponse.builder()
        .accessToken(newAccessToken)
        .refreshToken(refreshToken)
        .customerId(customer.getId())
        .name(customer.getName())
        .email(customer.getEmail())
        .username(customer.getUsername())
        .build();
    }

    @Override
    public void changePassword(Long customerId,
            ChangePasswordRequest request) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found"));

        boolean isMatch = passwordEncoder.matches(
                request.getOldPassword(),
                customer.getPassword());

        if (!isMatch) {
            throw new AccessDeniedException(
                    "Old password incorrect");
        }

        customer.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        customerRepository.save(customer);
    }
}