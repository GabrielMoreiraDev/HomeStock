package com.homestock.group_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupDto {
    private String group_name;
    private String group_description;
    private String group_access_code;
    private Float group_budget;
    private LocalDateTime group_createdAt;
    private String user_email;
    private String user_role;
}
