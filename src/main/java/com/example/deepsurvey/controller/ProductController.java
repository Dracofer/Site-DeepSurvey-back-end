
package com.example.deepsurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.deepsurvey.model.Category;
import com.example.deepsurvey.model.Product;
import com.example.deepsurvey.repository.CategoryRepository;
import com.example.deepsurvey.repository.ProductRepository;

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
    @GetMapping("/search")
    public List<Product> search(@RequestParam String q) {
        return productRepository.findByNameContainingIgnoreCase(q);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }
    
    @GetMapping("/offers")
    public List<Product> getOffers() {
        return productRepository.findByOnSaleTrue();
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        if (p.getCategory() != null && p.getCategory().getId() != null) {
            Category c = categoryRepository.findById(p.getCategory().getId()).orElse(null);
            p.setCategory(c);
        }
        return productRepository.save(p);
    }
    @GetMapping("/category/{id}")
    public List<Product> getByCategory(@PathVariable Long id) {
        return productRepository.findByCategoryId(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        Product existing = productRepository.findById(id).orElseThrow();

        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setStock(p.getStock());
        existing.setImageUrl(p.getImageUrl());
        existing.setOnSale(p.getOnSale());
        existing.setSalePrice(p.getSalePrice());

        // 🔥 ATUALIZAÇÃO DAS IMAGENS EXTRAS
        existing.setImages(p.getImages());

        if (p.getCategory() != null && p.getCategory().getId() != null) {
            existing.setCategory(
                categoryRepository.findById(p.getCategory().getId()).orElse(null)
            );
        } else {
            existing.setCategory(null);
        }

        return productRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
