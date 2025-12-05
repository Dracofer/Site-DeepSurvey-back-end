package com.example.deepsurvey.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.deepsurvey.model.User;
import com.example.deepsurvey.repository.UserRepository;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner initAdmin(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRoles(Set.of("ROLE_ADMIN"));

                userRepo.save(admin);
                System.out.println(">>> ADMIN criado com sucesso (admin / admin123).");
            }
        };
    }
}