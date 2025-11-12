package com.homestock.inventory_service.dto;

import com.homestock.inventory_service.enums.Type;
import com.homestock.inventory_service.enums.Unity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name should be 100 or less digits")
    private String name;

    @NotNull(message = "Type is required")
    private Type type;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Quantity can not be negative!")
    private Float quantity;

    @NotNull(message = "Unity is required")
    private Unity unity;

    @NotNull(message = "Threshold is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Threshold can not be negative!")
    private Float threshold;

    @NotNull(message = "Ideal point is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Ideal point can not be 0 or less!")
    private Float idealPoint;
}
