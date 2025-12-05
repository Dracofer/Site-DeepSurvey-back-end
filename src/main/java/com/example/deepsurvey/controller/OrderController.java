package com.example.deepsurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.deepsurvey.dto.CheckoutRequest;
import com.example.deepsurvey.model.*;
import com.example.deepsurvey.repository.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/checkout")
    @Transactional
    public ResponseEntity<?> checkout(@Valid @RequestBody CheckoutRequest req) {

        List<CartItem> cart = cartRepo.findBySessionId(req.getSessionId());
        if (cart == null || cart.isEmpty())
            return ResponseEntity.badRequest().body("Carrinho vazio");

        String fullAddress =
                req.getStreet() + ", " + req.getNumber() +
                        (req.getComplement() != null ? " - " + req.getComplement() : "") +
                        ", " + req.getRegion() +
                        ", CEP: " + req.getCep() +
                        (req.getReference() != null ? " (Ref: " + req.getReference() + ")" : "");

        OrderEntity order = new OrderEntity();
        order.setName(req.getName());
        order.setPhone(req.getPhone());
        order.setAddress(fullAddress);
        order.setPaymentMethod(req.getPaymentMethod());
        order.setStatus("PENDING");

        List<OrderItem> items = cart.stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getPrice());
            oi.setOrder(order);
            return oi;
        }).collect(Collectors.toList());

        order.setItems(items);

        double itemsTotal = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotal(itemsTotal);

        OrderEntity saved = orderRepo.save(order);

        cartRepo.deleteBySessionId(req.getSessionId());

        return ResponseEntity.ok(saved);
    }
}