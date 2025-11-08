package com.homestock.group_service.repository;

import com.homestock.group_service.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
