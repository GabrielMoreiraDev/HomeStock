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
public class UserCreatedEvent {
    private long id;

    private String email;

    private String name;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
