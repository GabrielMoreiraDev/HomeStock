package com.homestock.shoppinglist_service.utils;

import com.homestock.shoppinglist_service.exception.NotFound;
import com.homestock.shoppinglist_service.repository.ListItemRepository;
import com.homestock.shoppinglist_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListItemUtils {
    private final ListItemRepository listItemRepository;

    public int findNextPosition(Long productId) {
        return listItemRepository.findTopByProductIdOrderByPositionDesc(productId)
                .map(item -> item.getPosition() + 1000)
                .orElse(1000);
    }
}
