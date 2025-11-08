package com.homestock.group_service.repository;

import com.homestock.group_service.model.Group;
import com.homestock.group_service.model.User;
import com.homestock.group_service.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    Optional<UserGroup> findByUserIdAndGroupId(Long userId, Long groupId);
    List<UserGroup> findAllByGroupId(Long groupId);

    @Query("SELECT ug.group FROM UserGroup ug WHERE ug.user = :user")
    List<Group> findAllGroupsByUser(@Param("user") User user);

    @Query("SELECT ug.user FROM UserGroup ug WHERE ug.group = :group")
    List<User> findAllUsersByGroup(@Param("group") Group group);

    Long countByGroupId(Long groupId);
}
