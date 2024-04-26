package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Category;
import com.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public List<Category> getCategoriesByIds(List<Integer> ids){
        return categoryRepository.findByIdsList(ids);
    }
}
