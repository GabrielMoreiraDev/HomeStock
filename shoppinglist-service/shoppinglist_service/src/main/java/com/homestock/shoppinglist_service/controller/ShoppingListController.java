package com.homestock.shoppinglist_service.controller;

import com.homestock.shoppinglist_service.dto.AddNoteDto;
import com.homestock.shoppinglist_service.dto.CreateListItemDto;
import com.homestock.shoppinglist_service.dto.ListItemDto;
import com.homestock.shoppinglist_service.dto.MoveItemDto;
import com.homestock.shoppinglist_service.model.User;
import com.homestock.shoppinglist_service.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shoppinglist")
@RequiredArgsConstructor
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @PostMapping("/addItem/{groupId}/{productId}")
    public ListItemDto addListItem(
            @PathVariable Long groupId, @PathVariable Long productId, @AuthenticationPrincipal User user,
            @RequestBody CreateListItemDto request
            ) {
        return shoppingListService.addListItem(user, groupId, productId, request);
    }

    @DeleteMapping("/removeItem/{groupId}/{productId}")
    public void removeListItem(
            @PathVariable Long groupId, @PathVariable Long productId, @AuthenticationPrincipal User user
    ) {
        shoppingListService.removeListItem(user, groupId, productId);
    }

    @PatchMapping("/moveItem/{groupId}/{productId}")
    public ListItemDto moveItem(
            @AuthenticationPrincipal User user, @PathVariable Long groupId, @PathVariable Long productId,
            @RequestBody MoveItemDto request
    ) {
        return shoppingListService.moveItem(
                user, groupId, productId, request.getProductBeforeId(), request.getProductAfterId()
        );
    }

    @PatchMapping("/addNote/{groupId}/{productId}")
    public ListItemDto addNote(
            @AuthenticationPrincipal User user, @PathVariable Long groupId, @PathVariable Long productId,
            @RequestBody AddNoteDto request
    ) {
        return shoppingListService.addNote(
                user, groupId, productId, request.getNote()
        );
    }
}
