package com.homestock.shoppinglist_service.service;

import com.homestock.shoppinglist_service.repository.ListItemRepository;
import com.homestock.shoppinglist_service.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingListService {
    private final ListItemRepository listItemRepository;
    private final UserGroupRepository userGroupRepository;
}
