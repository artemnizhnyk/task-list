package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.service.AuthService;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtRequest;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtResponse;
import org.springframework.stereotype.Service;

@Service
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
