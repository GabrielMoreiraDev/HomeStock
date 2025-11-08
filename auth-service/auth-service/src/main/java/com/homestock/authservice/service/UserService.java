package com.homestock.authservice.service;

import com.homestock.authservice.config.JwtService;
import com.homestock.authservice.dto.*;
import com.homestock.authservice.exception.AlreadyExists;
import com.homestock.authservice.exception.Invalid;
import com.homestock.authservice.kafka.UserCreatedProducer;
import com.homestock.authservice.mapper.UserMapper;
import com.homestock.authservice.model.User;
import com.homestock.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserCreatedProducer userCreatedProducer;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

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
        userCreatedProducer.sendUserCreatedEvent(user);

        return userMapper.toDTO(user);
    }



    public String refresh(RefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        String role = jwtService.extractRole(refreshToken);
        String name = jwtService.extractName(refreshToken);


        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("name", name);

        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new Invalid("Invalid refresh token");
        }

        return jwtService.generateToken(claims, userDetails);
    }

    public AuthenticationResponse login(UserLoginDto request){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();

            Map<String, Object> claims = new HashMap<>();
            claims.put("name", user.getName());

            var jwtToken = jwtService.generateToken(claims, user);
            var refreshToken = jwtService.generateRefreshToken(claims, user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            throw new Invalid("Invalid user");
        }

    }
}
