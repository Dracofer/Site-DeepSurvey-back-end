package com.example.deepsurvey.controller;

import com.example.deepsurvey.dto.UserResponseDTO;
import com.example.deepsurvey.model.User;
import com.example.deepsurvey.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/me")
    public UserResponseDTO me(Authentication auth) {

        String username = auth.getName();

        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDTO(
                u.getId(),
                u.getUsername(),
                u.getRoles()
        );
        }
}