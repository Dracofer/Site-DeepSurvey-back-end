package com.example.deepsurvey.dto;

import java.util.Set;

public class UserResponseDTO {
    public Long id;
    public String username;
    public Set<String> roles;

    public UserResponseDTO(Long id, String username, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}