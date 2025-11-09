package com.homestock.inventory_service.repository;

import com.homestock.inventory_service.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    Optional<UserGroup> findByUserIdAndGroupId(Long userId, Long groupId);
}
