package com.example.deepsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.deepsurvey.model.CartItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findBySessionId(String sessionId);
    Optional<CartItem> findBySessionIdAndProductId(String sessionId, Long productId);
    
    void deleteBySessionId(String sessionId);
}