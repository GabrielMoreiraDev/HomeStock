package com.homestock.group_service.service;

import com.homestock.group_service.dto.GroupCreateDto;
import com.homestock.group_service.dto.GroupDto;
import com.homestock.group_service.dto.UserDto;
import com.homestock.group_service.dto.UserGroupDto;
import com.homestock.group_service.exception.AlreadyExists;
import com.homestock.group_service.exception.Invalid;
import com.homestock.group_service.exception.NoPermission;
import com.homestock.group_service.exception.NotFound;
import com.homestock.group_service.mapper.GroupMapper;
import com.homestock.group_service.mapper.UserGroupMapper;
import com.homestock.group_service.mapper.UserMapper;
import com.homestock.group_service.model.Group;
import com.homestock.group_service.model.User;
import com.homestock.group_service.model.UserGroup;
import com.homestock.group_service.repository.GroupRepository;
import com.homestock.group_service.repository.UserGroupRepository;
import com.homestock.group_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final GroupMapper groupMapper;
    private final UserGroupMapper userGroupMapper;
    private final UserMapper userMapper;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    private String generateAccessCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    private UserGroup createUserGroup(User user, Group group, String role) {
        UserGroup userGroup = UserGroup.builder()
                .user(user)
                .group(group)
                .role(role)
                .build();

        userGroupRepository.save(userGroup);

        return userGroup;
    }

    public List<GroupDto> getUserGroups(User user) {
        return userGroupRepository.findAllGroupsByUser(user).stream()
                .map(groupMapper::toDto)
                .toList();
    }

    public List<UserDto> getMembers(Long groupId, User user) {
        UserGroup userGroup = userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId).orElseThrow(() -> new NotFound("User is not part of the group!"));

        return userGroupRepository.findAllUsersByGroup(userGroup.getGroup()).stream()
                .map(userMapper::toDto)
                .toList();
    }

    public GroupDto createGroup(GroupCreateDto newGroup, User user) {

        Group group = Group.builder()
                .name(newGroup.getName())
                .description(newGroup.getDescription())
                .budget(newGroup.getBudget())
                .build();

        final int MAX_TRIES = 5;
        int attempt = 0;
        boolean saved = false;


        while (!saved) {
            group.setAccessCode(generateAccessCode());
            try {
                group = groupRepository.save(group);
                saved = true;
            } catch (DataIntegrityViolationException e) {
                attempt++;
                if (attempt >= MAX_TRIES) {
                    throw new RuntimeException("Could not generate a unique access code after " + MAX_TRIES + " attempts", e);
                }
            }
        }

        createUserGroup(user, group, "ADMIN");

        return groupMapper.toDto(group);
    }

    public void deleteGroup(Long id, User user) {
        UserGroup userGroup = userGroupRepository.findByUserIdAndGroupId(user.getId(), id).orElseThrow(() -> new NoPermission("User is not part of the group!"));
        Group group = userGroup.getGroup();

        if (group.getId() != id) {
            throw new NoPermission("User is not part of the group");
        } else if (!userGroup.getRole().equals("ADMIN")) {
            throw new NoPermission("User does not have permission");
        }

        groupRepository.delete(group);
    }

    public UserGroupDto joinGroup(String accessCode, User user) {
        Group group = groupRepository.findByAccessCode(accessCode).orElseThrow(() -> new NotFound("Group not found"));

        if (userGroupRepository.findByUserIdAndGroupId(user.getId(), group.getId()).isPresent()) {
            throw new AlreadyExists("User is already part of the group!");
        }

        UserGroup userGroup = createUserGroup(user, group, "USER");

        return userGroupMapper.toDto(group, userGroup);
    }

    public void leaveGroup(Long groupId, User user) {
        UserGroup userGroup = userGroupRepository.findByUserIdAndGroupId(user.getId(), groupId).orElseThrow(() -> new NoPermission("User is not part of the group!"));

        if (userGroupRepository.countByGroupId(groupId) == 1) {
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new NotFound("Group not found"));
            groupRepository.delete(group);
        } else {
            userGroupRepository.delete(userGroup);
        }

    }
}
