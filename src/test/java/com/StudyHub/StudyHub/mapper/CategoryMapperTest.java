package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.model.Category;
import com.StudyHub.StudyHub.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryMapperTest {

    @Test
    public void testToCategoryDTO() {
        // Arrange
        Category category = new Category("Test Category");
        category.setId(1L);

        // Act
        CategoryDTO categoryDTO = CategoryMapper.toCategoryDTO(category);

        // Assert
        assertNotNull(categoryDTO);
        assertEquals(1L, categoryDTO.getId());
        assertEquals("Test Category", categoryDTO.getName());
    }

    @Test
    public void testToCategory() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Test Category");

        // Act
        Category category = CategoryMapper.toCategory(categoryDTO);

        // Assert
        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testToCategoryDTO_Null() {
        // Act
        CategoryDTO categoryDTO = CategoryMapper.toCategoryDTO(null);

        // Assert
        assertNull(categoryDTO);
    }

    @Test
    public void testToCategory_Null() {
        // Act
        Category category = CategoryMapper.toCategory(null);

        // Assert
        assertNull(category);
    }
}
