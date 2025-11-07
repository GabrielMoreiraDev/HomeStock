package com.homestock.authservice.controller;

import com.homestock.authservice.dto.UserCreateDto;
import com.homestock.authservice.dto.UserDto;
import com.homestock.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserCreateDto request) {
        return userService.register(request);
    }
}
