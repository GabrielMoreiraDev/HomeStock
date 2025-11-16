package com.homestock.shoppinglist_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddListItemEvent {
    private Float quantity;
    private Long productId;
}
