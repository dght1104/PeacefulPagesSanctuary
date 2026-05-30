package com.peacefulpagessanctuary.controller;

import com.peacefulpagessanctuary.dto.request.auth.ChangePasswordRequest;
import com.peacefulpagessanctuary.dto.request.auth.LoginRequest;
import com.peacefulpagessanctuary.dto.request.auth.RefreshTokenRequest;
import com.peacefulpagessanctuary.dto.request.auth.RegisterRequest;
import com.peacefulpagessanctuary.dto.response.auth.JwtResponse;
import com.peacefulpagessanctuary.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/register")
    public JwtResponse register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/refresh-token")
    public JwtResponse refreshToken(
            @RequestBody RefreshTokenRequest request) {

        return authService.refreshToken(request);
    }

    @PutMapping("/change-password/{customerId}")
    public void changePassword(
            @PathVariable Long customerId,
            @RequestBody ChangePasswordRequest request) {

        authService.changePassword(customerId, request);
    }
}