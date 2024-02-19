package com.artemnizhnyk.tasklist.web.controller;

import com.artemnizhnyk.tasklist.service.AuthService;
import com.artemnizhnyk.tasklist.service.UserService;
import com.artemnizhnyk.tasklist.web.dto.UserDto;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtRequest;
import com.artemnizhnyk.tasklist.web.dto.auth.JwtResponse;
import com.artemnizhnyk.tasklist.web.dto.validation.OnCreate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Controller", description = "Auth API")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody final JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody final UserDto userDto) {
        return userService.createOrThrowException(userDto);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody final String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
