package com.example.lojazaplike.controller;

import com.example.lojazaplike.model.*;
import com.example.lojazaplike.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired private CartItemRepository cartRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderItemRepository orderItemRepo;

    // 🔥 FECHAR PEDIDO SEM LOGIN
    @PostMapping("/checkout")
    public OrderEntity checkout(@RequestParam String sessionId, @RequestBody OrderEntity data) {

        List<CartItem> cart = cartRepo.findBySessionId(sessionId);
        if (cart.isEmpty()) {
            throw new RuntimeException("Carrinho vazio.");
        }

        // Criar pedido
        OrderEntity order = new OrderEntity();
        order.setName(data.getName());
        order.setPhone(data.getPhone());
        order.setAddress(data.getAddress());
        order.setStatus("PENDING");

        // Criar itens do pedido
        List<OrderItem> items = cart.stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice());
            oi.setOrder(order);
            return oi;
        }).collect(Collectors.toList());

        order.setItems(items);

        // Calcular total
        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotal(total);

        // Salvar pedido
        orderRepo.save(order);

        // Limpar carrinho
        cartRepo.deleteBySessionId(sessionId);

        return order;
    }
}