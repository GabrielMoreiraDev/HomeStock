package com.homestock.inventory_service.dto;

import com.homestock.inventory_service.enums.Type;
import com.homestock.inventory_service.enums.Unity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Type type;
    private Float quantity;
    private Unity unity;
    private Float threshold;
    private Float idealPoint;
    private Long groupId;
    private LocalDateTime createdAt;
}
