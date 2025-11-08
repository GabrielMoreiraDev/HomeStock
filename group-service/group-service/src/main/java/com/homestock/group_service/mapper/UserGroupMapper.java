package com.homestock.group_service.mapper;

import com.homestock.group_service.dto.UserGroupDto;
import com.homestock.group_service.model.Group;
import com.homestock.group_service.model.UserGroup;
import org.springframework.stereotype.Component;

@Component
public class UserGroupMapper {

    public UserGroupDto toDto(Group group, UserGroup userGroup) {
        UserGroupDto dto = new UserGroupDto();
        dto.setGroup_budget(group.getBudget());
        dto.setGroup_name(group.getName());
        dto.setGroup_description(group.getDescription());
        dto.setGroup_createdAt(group.getCreatedAt());
        dto.setGroup_access_code(group.getAccess_code());
        dto.setUser_role(userGroup.getRole());
        dto.setUser_email(userGroup.getUser().getEmail());
        return dto;
    }
}
