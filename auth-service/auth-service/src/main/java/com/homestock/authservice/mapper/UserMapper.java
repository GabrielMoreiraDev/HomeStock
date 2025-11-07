package com.homestock.authservice.mapper;

import com.homestock.authservice.dto.UserDto;
import com.homestock.authservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDTO(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setCreatedAt(userDto.getCreatedAt());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setId(userDto.getId());
        return user;
    }
}