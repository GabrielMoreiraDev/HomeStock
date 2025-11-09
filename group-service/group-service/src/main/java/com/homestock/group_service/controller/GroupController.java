package com.homestock.group_service.controller;

import com.homestock.group_service.dto.*;
import com.homestock.group_service.model.User;
import com.homestock.group_service.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create")
    public GroupDto createGroup(@RequestBody GroupCreateDto request, @AuthenticationPrincipal User user) {
        return groupService.createGroup(request, user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long id, @AuthenticationPrincipal User user) {
        groupService.deleteGroup(id, user);
    }

    @PostMapping("/join/{accessCode}")
    public UserGroupDto joinGroup(@PathVariable String accessCode, @AuthenticationPrincipal User user) {
        return groupService.joinGroup(accessCode, user);
    }

    @DeleteMapping("/leave/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leaveGroup(@PathVariable Long groupId, @AuthenticationPrincipal User user) {
        groupService.leaveGroup(groupId, user);
    }

    @GetMapping("/my-groups")
    public List<GroupDto> getUserGroups(@AuthenticationPrincipal User user) {
        return groupService.getUserGroups(user);
    }

    @GetMapping("/members/{groupId}")
    public List<UserDto> getMembers(@PathVariable Long groupId, @AuthenticationPrincipal User user) {
        return groupService.getMembers(groupId, user);
    }

    @PutMapping("/member/toggle-role/{groupId}/{userId}")
    public ToggleRoleDto toggleUserRole(
            @PathVariable Long groupId, @AuthenticationPrincipal User user, @PathVariable Long userId
    ) {
        return groupService.toggleRole(groupId, user, userId);
    }
}
