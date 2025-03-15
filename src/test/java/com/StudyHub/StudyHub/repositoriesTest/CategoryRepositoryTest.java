package com.StudyHub.StudyHub.repositoriesTest;

import com.StudyHub.StudyHub.model.Category;
import com.StudyHub.StudyHub.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void shouldSaveCategory() {
        Category category = new Category("Science");
        Category savedCategory = categoryRepository.save(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindCategoryById() {
        Category category = new Category("Math");
        Category savedCategory = categoryRepository.save(category);

        Category foundCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);

        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getId()).isEqualTo(savedCategory.getId());
        assertThat(foundCategory.getName()).isEqualTo(savedCategory.getName());
    }

    @Test
    public void shouldDeleteCategory() {
        Category category = new Category("History");
        Category savedCategory = categoryRepository.save(category);

        categoryRepository.delete(savedCategory);

        Category deletedCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);
        assertThat(deletedCategory).isNull();
    }
}
