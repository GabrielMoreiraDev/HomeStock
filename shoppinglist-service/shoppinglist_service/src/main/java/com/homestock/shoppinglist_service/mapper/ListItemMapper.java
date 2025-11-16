package com.homestock.shoppinglist_service.mapper;

import com.homestock.shoppinglist_service.dto.ListItemDto;
import com.homestock.shoppinglist_service.model.ListItem;
import org.springframework.stereotype.Component;

@Component
public class ListItemMapper {
    public ListItemDto toDto(ListItem listItem) {
        return ListItemDto.builder()
                .position(listItem.getPosition())
                .note(listItem.getNote())
                .id(listItem.getId())
                .productId(listItem.getProductId())
                .createdAt(listItem.getCreatedAt())
                .quantity(listItem.getQuantity())
                .build();
    }
}
