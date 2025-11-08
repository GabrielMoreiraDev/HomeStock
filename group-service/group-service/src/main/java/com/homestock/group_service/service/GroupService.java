package com.homestock.group_service.service;

import com.homestock.group_service.dto.GroupCreateDto;
import com.homestock.group_service.dto.GroupDto;
import com.homestock.group_service.mapper.GroupMapper;
import com.homestock.group_service.model.Group;
import com.homestock.group_service.repository.GroupRepository;
import com.homestock.group_service.repository.UserGroupRepository;
import com.homestock.group_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final GroupMapper groupMapper;

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

    public GroupDto createGroup(GroupCreateDto newGroup) {

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

        return groupMapper.toDto(group);
    }
}
