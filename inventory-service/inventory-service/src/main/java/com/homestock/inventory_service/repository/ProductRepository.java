package com.homestock.inventory_service.repository;

import com.homestock.inventory_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByGroupId(Long groupId);
}
