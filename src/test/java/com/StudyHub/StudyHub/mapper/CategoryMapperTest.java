package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.CategoryDTO;
import com.StudyHub.StudyHub.model.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryMapperTest {

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    public void testToCategoryDTO() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        // Act
        CategoryDTO categoryDTO = categoryMapper.toDto(category);

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
        Category category = categoryMapper.toEntity(categoryDTO);

        // Assert
        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Test Category", category.getName());
    }

    @Test
    public void testToCategoryDTO_Null() {
        assertNull(categoryMapper.toDto(null));
    }

    @Test
    public void testToCategory_Null() {
        assertNull(categoryMapper.toEntity(null));
    }
}
