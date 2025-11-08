package com.homestock.group_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name should be 100 or less digits")
    private String name;

    @Size(max = 100, message = "Description should be 255 or less digits")
    private String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "Budget can not be negative")
    private Float budget;
}
