package com.homestock.group_service.controller;

import com.homestock.group_service.dto.GroupCreateDto;
import com.homestock.group_service.dto.GroupDto;
import com.homestock.group_service.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create")
    public GroupDto createGroup(@RequestBody GroupCreateDto request) {
        return groupService.createGroup(request);
    }
}
