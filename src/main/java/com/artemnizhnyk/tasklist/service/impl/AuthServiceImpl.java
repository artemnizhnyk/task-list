package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.auth.JwtRequest;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtResponse;

public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponse login(final JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(final String refreshToken) {
        return null;
    }
}
