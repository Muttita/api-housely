package com.cp.kku.housely.service;

import org.springframework.stereotype.Service;

import com.cp.kku.housely.model.Category;
import com.cp.kku.housely.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) { return categoryRepository.findById(id);}

    public Category createCategory(Category category) { return categoryRepository.save(category); }

    public Optional<Category> updateCategory(Long id, Category categoryDetails) {
        return categoryRepository.findById(id).map(category -> {
            category.setCategoryName(categoryDetails.getCategoryName());
            category.setDescription(categoryDetails.getDescription());
            category.setImageBase64(categoryDetails.getImageBase64());
            category.setProductsInCategory(categoryDetails.getProductsInCategory());
            return categoryRepository.save(category);
        });
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
