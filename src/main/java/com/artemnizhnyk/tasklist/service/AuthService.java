package com.artemnizhnyk.tasklist.service;

import com.artemnizhnyk.tasklist.web.dto.auth.JwtRequest;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(final JwtRequest loginRequest);

    JwtResponse refresh(final String refreshToken);
}
