
package com.example.lojazaplike.controller;

import com.example.lojazaplike.model.Product;
import com.example.lojazaplike.model.Category;
import com.example.lojazaplike.repository.ProductRepository;
import com.example.lojazaplike.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        if (p.getCategory() != null && p.getCategory().getId() != null) {
            Category c = categoryRepository.findById(p.getCategory().getId()).orElse(null);
            p.setCategory(c);
        }
        return productRepository.save(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        Product existing = productRepository.findById(id).orElseThrow();
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setStock(p.getStock());
        existing.setImageUrl(p.getImageUrl()); // <-- CORREÇÃO AQUI

        if (p.getCategory() != null && p.getCategory().getId() != null) {
            existing.setCategory(categoryRepository.findById(p.getCategory().getId()).orElse(null));
        }

        return productRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
