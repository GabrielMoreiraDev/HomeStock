package com.homestock.shoppinglist_service.repository;

import com.homestock.shoppinglist_service.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
}
