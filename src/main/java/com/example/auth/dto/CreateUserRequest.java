package com.example.auth.dto;

import com.example.auth.model.Role;

import java.util.Set;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Set<Role> roles
) {
}
