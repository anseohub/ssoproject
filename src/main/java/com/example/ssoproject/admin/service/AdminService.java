package com.example.ssoproject.admin.service;

import com.example.ssoproject.domain.entity.User;
import com.example.ssoproject.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public Page<User> getUsers(String search, String sortField, String direction, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        if (search == null || search.isEmpty()) {
            return userRepository.findAll(pageable);
        } else {
            return userRepository.searchUsers(search, pageable);
        }
    }
}
