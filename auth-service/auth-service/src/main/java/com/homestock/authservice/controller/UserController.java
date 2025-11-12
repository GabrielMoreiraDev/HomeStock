package com.homestock.authservice.controller;

import com.homestock.authservice.dto.*;
import com.homestock.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody UserLoginDto request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserCreateDto request) {
        return userService.register(request);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestBody RefreshRequest request) {
        return userService.refresh(request);
    }
}
