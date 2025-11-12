package com.homestock.inventory_service.controller;

import com.homestock.inventory_service.dto.ProductCreateDto;
import com.homestock.inventory_service.dto.ProductDto;
import com.homestock.inventory_service.dto.UpdateProductQuantityDto;
import com.homestock.inventory_service.model.User;
import com.homestock.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final ProductService productService;

    @PostMapping("/create/{groupId}")
    public ProductDto createProduct(
            @AuthenticationPrincipal User user, @PathVariable Long groupId, @RequestBody ProductCreateDto request
    ) {
        return productService.createProduct(request, user, groupId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(
            @AuthenticationPrincipal User user, @PathVariable Long productId
    ) {
        productService.deleteProduct(user, productId);
    }

    @GetMapping("/{groupId}")
    public List<ProductDto> getInventory(
            @AuthenticationPrincipal User user, @PathVariable Long groupId
    ) {
        return productService.getInventory(user, groupId);
    }

    @PutMapping("/update-quantity/{productId}")
    public ProductDto updateQuantity(
            @AuthenticationPrincipal User user, @PathVariable Long productId, @RequestBody UpdateProductQuantityDto request
    ) {
        return productService.updateQuantity(user, productId, request);
    }
}
