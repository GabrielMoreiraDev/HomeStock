package com.homestock.group_service.mapper;

import com.homestock.group_service.dto.GroupDto;
import com.homestock.group_service.model.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public GroupDto toDto(Group group) {
        GroupDto dto = new GroupDto();
        dto.setName(group.getName());
        dto.setBudget(group.getBudget());
        dto.setDescription(group.getDescription());
        dto.setCreatedAt(group.getCreatedAt());
        dto.setAccess_code(group.getAccessCode());
        return dto;
    }

    public Group toEntity(GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setBudget(groupDto.getBudget());
        group.setDescription(groupDto.getDescription());
        group.setCreatedAt(groupDto.getCreatedAt());
        group.setAccessCode(groupDto.getAccess_code());
        return group;
    }
}
