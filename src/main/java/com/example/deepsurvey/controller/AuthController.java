
package com.example.deepsurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.deepsurvey.model.User;
import com.example.deepsurvey.repository.UserRepository;
import com.example.deepsurvey.security.JwtUtil;

import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        var opt = userRepository.findByUsername(username);
        if (opt.isEmpty())
            return Map.of("error", "invalid_credentials");

        User u = opt.get();

        if (!passwordEncoder.matches(password, u.getPassword())) {
            return Map.of("error", "invalid_credentials");
        }

        // 🔥 AGORA INCLUINDO AS ROLES NO TOKEN
        String token = jwtUtil.generateToken(username, u.getRoles());

        return Map.of(
            "token", token,
            "username", username,
            "roles", u.getRoles()  // <-- 🔥 IMPORTANTE
        );
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        if (userRepository.findByUsername(username).isPresent()) {
            return Map.of("error", "username_taken");
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(password));
        u.setRoles(Set.of("ROLE_USER"));

        userRepository.save(u);

        // 🔥 TOKEN COM ROLES
        String token = jwtUtil.generateToken(username, u.getRoles());

        return Map.of(
            "token", token,
            "username", username,
            "roles", u.getRoles()
        );
    }
}