package com.homestock.group_service.mapper;

import com.homestock.group_service.dto.GroupDto;
import com.homestock.group_service.dto.UserDto;
import com.homestock.group_service.model.Group;
import com.homestock.group_service.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
