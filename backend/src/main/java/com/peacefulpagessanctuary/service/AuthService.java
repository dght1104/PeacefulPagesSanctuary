package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.dto.request.auth.LoginRequest;
import com.peacefulpagessanctuary.dto.request.auth.RegisterRequest;
import com.peacefulpagessanctuary.dto.request.auth.RefreshTokenRequest;
import com.peacefulpagessanctuary.dto.request.auth.ChangePasswordRequest;
import com.peacefulpagessanctuary.dto.response.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(LoginRequest request);

    JwtResponse register(RegisterRequest request);

    JwtResponse refreshToken(RefreshTokenRequest request);

    void changePassword(Long customerId, ChangePasswordRequest request);
}