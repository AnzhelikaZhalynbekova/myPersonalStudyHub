package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
    Category updateCategory(Long id, Category category);
    boolean deleteCategory(Long id);
}
