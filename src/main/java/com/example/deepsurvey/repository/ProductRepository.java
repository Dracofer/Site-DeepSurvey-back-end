
package com.example.deepsurvey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deepsurvey.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategoryId(Long id);
	List<Product> findByNameContainingIgnoreCase(String name);
	List<Product> findByOnSaleTrue();
}
