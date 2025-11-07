package com.homestock.authservice.service;

import com.homestock.authservice.dto.UserCreateDto;
import com.homestock.authservice.dto.UserDto;
import com.homestock.authservice.exception.AlreadyExists;
import com.homestock.authservice.exception.Invalid;
import com.homestock.authservice.mapper.UserMapper;
import com.homestock.authservice.model.User;
import com.homestock.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto register(UserCreateDto newUser) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new AlreadyExists("User already exists");
        }

        if (newUser.getEmail().length() > 255 || newUser.getName().length() > 100 || newUser.getPassword().length() > 16) {
            throw new Invalid("Invalid user data");
        }

        var user = User.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .build();

        userRepository.save(user);

        return userMapper.toDTO(user);
    }
}
