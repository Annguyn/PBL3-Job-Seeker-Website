package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(
        value = "select * from category",
        nativeQuery = true)
    List<Category> getAllCategories();
     
}
