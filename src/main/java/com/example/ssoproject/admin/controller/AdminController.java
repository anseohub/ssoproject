package com.example.ssoproject.admin.controller;

import com.example.ssoproject.admin.service.AdminService;
import com.example.ssoproject.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public Page<User> getUsers(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return adminService.getUsers(search, sortField, direction, page, size);
    }
}
