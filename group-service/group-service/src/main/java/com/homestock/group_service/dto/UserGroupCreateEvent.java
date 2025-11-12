package com.homestock.group_service.dto;

import com.homestock.group_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupCreateEvent {
    private Long id;
    private Long userId;
    private Long groupId;
    private Role role;
}
