package com.homestock.shoppinglist_service.service;

import com.homestock.shoppinglist_service.dto.CreateListItemDto;
import com.homestock.shoppinglist_service.dto.ListItemDto;
import com.homestock.shoppinglist_service.exception.Invalid;
import com.homestock.shoppinglist_service.exception.NoPermission;
import com.homestock.shoppinglist_service.exception.NotFound;
import com.homestock.shoppinglist_service.mapper.ListItemMapper;
import com.homestock.shoppinglist_service.model.ListItem;
import com.homestock.shoppinglist_service.model.User;
import com.homestock.shoppinglist_service.repository.ListItemRepository;
import com.homestock.shoppinglist_service.repository.ProductRepository;
import com.homestock.shoppinglist_service.repository.UserGroupRepository;
import com.homestock.shoppinglist_service.utils.ListItemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingListService {
    private final ListItemRepository listItemRepository;
    private final UserGroupRepository userGroupRepository;
    private final ProductRepository productRepository;
    private final ListItemUtils listItemUtils;
    private final ListItemMapper listItemMapper;

    private boolean checkUserNotInGroup(User user, Long groupId) {
        return userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId).isEmpty();
    }

    private boolean checkProductNotInGroup(Long productId, Long groupId) {
        return !productRepository.findById(productId).orElseThrow(
                () -> new NotFound("Product not Found!")).getGroupId().equals(groupId);
    }

    public ListItemDto addListItem(User user, Long groupId, Long productId, CreateListItemDto request) {
        if (checkUserNotInGroup(user, groupId)) {
            throw new NoPermission("User does not have permission!");
        }

        if (checkProductNotInGroup(productId, groupId)) {
            throw new NoPermission("User does not have permission!");
        }

        int position = listItemUtils.findNextPosition(productId);

        ListItem newListItem = ListItem.builder()
                .note(request.getNote())
                .quantity(request.getQuantity())
                .productId(productId)
                .position(position).build();

        listItemRepository.save(newListItem);

        ListItemDto listItemDto = listItemMapper.toDto(newListItem);
        listItemDto.setGroupId(groupId);

        return listItemDto;

    }

    public void removeListItem(User user, Long groupId, Long productId) {
        if (checkUserNotInGroup(user, groupId)) {
            throw new NoPermission("User does not have permission!");
        }

        if (checkProductNotInGroup(productId, groupId)) {
            throw new NoPermission("User does not have permission!");
        }

        ListItem listItem = listItemRepository.findByProductId(productId).orElseThrow(
                () -> new NotFound("List item not found!")
        );

        listItemRepository.delete(listItem);
    }

    public ListItemDto moveItem(User user, Long groupId, Long productId, Long beforeProductId, Long afterProductId) {
        if (checkUserNotInGroup(user, groupId)) throw new NoPermission("User does not have permission!");
        if (checkProductNotInGroup(productId, groupId)) throw new NoPermission("User does not have permission!");

        ListItem item = listItemRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFound("List item not found!"));

        int newPosition;

        if (beforeProductId != null && afterProductId != null) {
            ListItem before = listItemRepository.findByProductId(beforeProductId)
                    .orElseThrow(() -> new NotFound("Before item not found!"));
            ListItem after = listItemRepository.findByProductId(afterProductId)
                    .orElseThrow(() -> new NotFound("After item not found!"));

            newPosition = (before.getPosition() + after.getPosition()) / 2;
        } else if (beforeProductId != null) {
            ListItem before = listItemRepository.findByProductId(beforeProductId)
                    .orElseThrow(() -> new NotFound("Before item not found!"));
            newPosition = before.getPosition() / 2;
        } else if (afterProductId != null) {
            ListItem after = listItemRepository.findByProductId(afterProductId)
                    .orElseThrow(() -> new NotFound("After item not found!"));
            newPosition = after.getPosition() + 1000;
        } else {
            ListItemDto listItemDto = listItemMapper.toDto(item);
            listItemDto.setGroupId(groupId);

            return listItemDto;
        }

        item.setPosition(newPosition);
        listItemRepository.save(item);

        ListItemDto listItemDto = listItemMapper.toDto(item);
        listItemDto.setGroupId(groupId);

        return listItemDto;
    }

    public ListItemDto addNote(User user, Long groupId, Long productId, String note) {
        if (checkUserNotInGroup(user, groupId)) throw new NoPermission("User does not have permission!");
        if (checkProductNotInGroup(productId, groupId)) throw new NoPermission("User does not have permission!");

        if (note == null || note.isBlank()) {
            throw new Invalid("Note must be something!");
        }

        ListItem item = listItemRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFound("List item not found!"));

        item.setNote(note);

        listItemRepository.save(item);

        ListItemDto listItemDto = listItemMapper.toDto(item);
        listItemDto.setGroupId(groupId);

        return listItemDto;
    }
}
