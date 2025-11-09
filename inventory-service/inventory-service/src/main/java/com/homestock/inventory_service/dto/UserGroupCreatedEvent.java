package com.homestock.inventory_service.dto;

import com.homestock.inventory_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupCreatedEvent {
    private Long id;
    private Long userId;
    private Long groupId;
    private Role role;
}
