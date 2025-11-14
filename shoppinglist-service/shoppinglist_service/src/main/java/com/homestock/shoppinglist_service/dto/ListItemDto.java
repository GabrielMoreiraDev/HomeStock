package com.homestock.shoppinglist_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListItemDto {
    private Long id;
    private Long groupId;
    private Long userId;
    private Float quantity;
    private String note;
    private Long position;
    private LocalDateTime createdAt;
}
