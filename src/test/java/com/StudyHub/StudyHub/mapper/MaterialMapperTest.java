package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.MaterialDTO;
import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.model.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest {

    private final MaterialMapper materialMapper = Mappers.getMapper(MaterialMapper.class);

    @Test
    void testToDto() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Programming");

        Material material = new Material();
        material.setId(10L);
        material.setTitle("Spring Boot Guide");
        material.setDescription("Learn Spring Boot in detail");
        material.setAuthor("Mr.Zhavlon");
        material.setFileUrl("https://example.com/spring-boot-guide");
        material.setCategory(category);

        // Act
        MaterialDTO materialDTO = materialMapper.toDto(material);

        // Assert
        assertNotNull(materialDTO);
        assertEquals(10L, materialDTO.getId());
        assertEquals("Spring Boot Guide", materialDTO.getTitle());
        assertEquals("Learn Spring Boot in detail", materialDTO.getDescription());
        assertEquals("Mr.Zhavlon", materialDTO.getAuthor());
        assertEquals("https://example.com/spring-boot-guide", materialDTO.getFileUrl());
        assertEquals(1L, materialDTO.getCategoryId());
    }

    @Test
    void testToEntity() {
        // Arrange
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(10L);
        materialDTO.setTitle("Spring Boot Guide");
        materialDTO.setDescription("Learn Spring Boot in detail");
        materialDTO.setAuthor("Mr.Zhavlon");
        materialDTO.setFileUrl("https://example.com/spring-boot-guide");
        materialDTO.setCategoryId(1L);

        // Act
        Material material = materialMapper.toEntity(materialDTO);

        // Assert
        assertNotNull(material);
        assertEquals(10L, material.getId());
        assertEquals("Spring Boot Guide", material.getTitle());
        assertEquals("Learn Spring Boot in detail", material.getDescription());
        assertEquals("Mr.Zhavlon", material.getAuthor());
        assertEquals("https://example.com/spring-boot-guide", material.getFileUrl());
        assertNotNull(material.getCategory());  // Проверяем, что категория создается
        assertEquals(1L, material.getCategory().getId());
    }

    @Test
    void testToDto_Null() {
        assertNull(materialMapper.toDto(null));
    }

    @Test
    void testToEntity_Null() {
        assertNull(materialMapper.toEntity(null));
    }
}
