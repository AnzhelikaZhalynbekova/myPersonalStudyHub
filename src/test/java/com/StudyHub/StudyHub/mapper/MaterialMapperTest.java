package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.MaterialDTO;
import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.model.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest {

    private final MaterialMapper materialMapper = Mappers.getMapper(MaterialMapper.class);

    @Test
    void testToDto() {
        Category category = new Category("Programming");
        Material material = new Material("Spring Boot Guide", "Learn Spring Boot in detail", "Mr.Zhavlon", "https://example.com/spring-boot-guide");
        material.setCategory(category);

        MaterialDTO materialDTO = materialMapper.toDto(material);

        assertEquals(material.getTitle(), materialDTO.getTitle());
        assertEquals(material.getDescription(), materialDTO.getDescription());
        assertEquals(material.getAuthor(), materialDTO.getAuthor());
        assertEquals(material.getUrl(), materialDTO.getFileUrl());
        assertEquals(material.getCategory().getId(), materialDTO.getCategoryId());
    }

    @Test
    void testToEntity() {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setTitle("Spring Boot Guide");
        materialDTO.setDescription("Learn Spring Boot in detail");
        materialDTO.setAuthor("Mr.Zhavlon");
        materialDTO.setFileUrl("https://example.com/spring-boot-guide");
        materialDTO.setCategoryId(1L);

        Material material = materialMapper.toEntity(materialDTO);

        assertEquals(materialDTO.getTitle(), material.getTitle());
        assertEquals(materialDTO.getDescription(), material.getDescription());
        assertEquals(materialDTO.getAuthor(), material.getAuthor());
        assertEquals(materialDTO.getFileUrl(), material.getUrl());
    }
}

