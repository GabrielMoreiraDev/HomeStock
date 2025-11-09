package com.homestock.inventory_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;

    @jakarta.validation.constraints.Email
    private String email;

    private String name;
}

