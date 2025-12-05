package com.example.deepsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.deepsurvey.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}