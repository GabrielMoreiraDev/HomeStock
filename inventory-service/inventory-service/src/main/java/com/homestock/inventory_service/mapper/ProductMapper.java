package com.homestock.inventory_service.mapper;

import com.homestock.inventory_service.dto.ProductCreateDto;
import com.homestock.inventory_service.dto.ProductDto;
import com.homestock.inventory_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setType(product.getType());
        dto.setQuantity(product.getQuantity());
        dto.setUnity(product.getUnity());
        dto.setGroupId(product.getGroupId());
        dto.setThreshold(product.getThreshold());
        dto.setIdealPoint(product.getIdealPoint());
        dto.setCreatedAt(product.getCreatedAt());
        return dto;
    }

    public Product toEntity(ProductCreateDto dto) {
        return Product.builder()
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .unity(dto.getUnity())
                .type(dto.getType())
                .idealPoint(dto.getIdealPoint())
                .threshold(dto.getThreshold())
                .build();
    }
}
