package com.homestock.group_service.service;

import com.homestock.group_service.repository.GroupRepository;
import com.homestock.group_service.repository.UserGroupRepository;
import com.homestock.group_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
}
