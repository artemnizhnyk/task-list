package com.artemnizhnyk.tasklist.service.impl;

import com.artemnizhnyk.tasklist.domain.model.user.User;
import com.artemnizhnyk.tasklist.service.AuthService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.service.mapper.UserMapper;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtRequest;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtResponse;
import com.artemnizhnyk.tasklist.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @Override
    public JwtResponse login(final JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        User user = userMapper.toEntity(
                userService.getByUsernameOrThrowException(loginRequest.getUsername())
        );
        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(
                user.getId(),
                user.getUsername(),
                user.getRoles())
        );
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(
                user.getId(),
                user.getUsername())
        );
        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(final String refreshToken) {
        return null;
    }
}
