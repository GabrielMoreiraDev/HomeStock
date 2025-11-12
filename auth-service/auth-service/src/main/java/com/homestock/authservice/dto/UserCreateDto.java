package com.homestock.authservice.dto;

import jakarta.validation.constraints.Email;
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
public class UserCreateDto {
    @Email(message = "Invalid Email")
    @NotBlank(message = "Emails is required")
    private String email;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name should be 100 or less digits")
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password should be 16 or less digits")
    private String password;
}
