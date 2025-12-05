
package com.example.deepsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deepsurvey.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
