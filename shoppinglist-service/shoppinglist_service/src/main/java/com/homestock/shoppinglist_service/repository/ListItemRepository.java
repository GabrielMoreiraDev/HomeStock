package com.homestock.shoppinglist_service.repository;

import com.homestock.shoppinglist_service.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    Optional<ListItem> findTopByProductIdOrderByPositionDesc(Long productId);
    Optional<ListItem> findByProductId(Long productId);
}
