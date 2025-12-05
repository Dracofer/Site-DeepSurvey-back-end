package com.example.deepsurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.deepsurvey.model.CartItem;
import com.example.deepsurvey.model.Product;
import com.example.deepsurvey.repository.CartItemRepository;
import com.example.deepsurvey.repository.ProductRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/{sessionId}")
    public List<CartItem> getCart(@PathVariable String sessionId) {
        return cartRepo.findBySessionId(sessionId);
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {

        CartItem existing = cartRepo
                .findBySessionIdAndProductId(item.getSessionId(), item.getProduct().getId())
                .orElse(null);

        Product p = productRepo.findById(item.getProduct().getId())
                .orElseThrow();

        if (existing != null) {
            existing.setQuantity(item.getQuantity());
            existing.setPrice(p.getPrice());
            return cartRepo.save(existing);
        }

        CartItem ci = new CartItem();
        ci.setSessionId(item.getSessionId());
        ci.setProduct(p);
        ci.setQuantity(item.getQuantity());
        ci.setPrice(p.getPrice());

        return cartRepo.save(ci);
    }

    @PostMapping("/remove")
    public void remove(@RequestBody Map<String, Object> data) {
        Long id = ((Number)data.get("itemId")).longValue();
        cartRepo.deleteById(id);
    }

    @PostMapping("/update")
    public CartItem updateQuantity(@RequestBody Map<String, Object> data) {

        Long id = ((Number) data.get("itemId")).longValue();
        Integer qty = (Integer) data.get("quantity");

        CartItem item = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item.setQuantity(qty);

        return cartRepo.save(item);
    }
}
