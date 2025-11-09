package com.homestock.inventory_service.service;

import com.homestock.inventory_service.dto.ProductCreateDto;
import com.homestock.inventory_service.dto.ProductDto;
import com.homestock.inventory_service.dto.ProductReachedThresholdEvent;
import com.homestock.inventory_service.dto.UpdateProductQuantityDto;
import com.homestock.inventory_service.enums.Role;
import com.homestock.inventory_service.exception.Invalid;
import com.homestock.inventory_service.exception.NoPermission;
import com.homestock.inventory_service.exception.NotFound;
import com.homestock.inventory_service.kafka.ProductReachedThresholdProducer;
import com.homestock.inventory_service.mapper.ProductMapper;
import com.homestock.inventory_service.model.Product;
import com.homestock.inventory_service.model.User;
import com.homestock.inventory_service.repository.ProductRepository;
import com.homestock.inventory_service.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserGroupRepository userGroupRepository;
    private final ProductMapper productMapper;
    private final ProductReachedThresholdProducer productReachedThresholdProducer;

    private boolean checkUserNotInGroup(User user, Long groupId) {
        return userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId).isEmpty();
    }

    private boolean checkUserRole(User user, Long groupId, Role role) {
        return userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId).orElseThrow(
                () -> new NotFound("Information not found")).getRole().equals(role);
    }

    public ProductDto createProduct(ProductCreateDto productCreateDto, User user, Long groupId) {
        if (checkUserNotInGroup(user, groupId)) {
            throw new NoPermission("User does not have permission!");
        }

        Product newProduct = productMapper.toEntity(productCreateDto);
        newProduct.setGroupId(groupId);

        productRepository.save(newProduct);

        return productMapper.toDto(newProduct);
    }

    public List<ProductDto> getInventory(User user, Long groupId) {
        if (checkUserNotInGroup(user, groupId)) {
            throw new NoPermission("User does not have permission!");
        }

        return productRepository.findAllByGroupId(groupId).stream()
                .map(productMapper::toDto)
                .toList();
    }

    public void deleteProduct(User user, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFound("Product not found"));

        if (!checkUserRole(user, product.getGroupId(), Role.ADMIN)) {
            throw new NoPermission("User does not have permission!");
        }

        productRepository.delete(product);
    }

    public ProductDto updateQuantity(User user, Long productId, UpdateProductQuantityDto request) {
        if (request.getQuantity() == 0) {
            throw new Invalid("Invalid quantity!");
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFound("Product not found"));

        if (checkUserNotInGroup(user, product.getGroupId())) {
            throw new NoPermission("User does not have permission!");
        }

        float newQuantity = product.getQuantity() + request.getQuantity();

        if (newQuantity < 0) {
            throw new Invalid("New quantity can not be negative!");
        }

        if (newQuantity <= product.getThreshold()) {
            ProductReachedThresholdEvent productReachedThresholdEvent = ProductReachedThresholdEvent.builder()
                            .quantity(product.getIdealPoint()).productId(productId).build();
            productReachedThresholdProducer.sendProductReachedThreshold(productReachedThresholdEvent);
        }

        product.setQuantity(newQuantity);
        productRepository.save(product);

        return productMapper.toDto(product);
    }
}
