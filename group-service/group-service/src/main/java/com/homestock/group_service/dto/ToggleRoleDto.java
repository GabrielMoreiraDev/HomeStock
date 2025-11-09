package com.homestock.group_service.dto;

import com.homestock.group_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToggleRoleDto {
    private Long userId;
    private Role role;
    private Long groupId;
}
