package com.StudyHub.StudyHub.controller;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.service.MaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private MaterialController materialController;

    private MockMvc mockMvc;
    private Material material;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(materialController).build();
        material = new Material("Material 1", "Description", "Author 1", "https://example.com/material1");
    }

    @Test
    void testGetAllMaterials() throws Exception {
        // Пример заглушки, когда возвращается один материал
        when(materialService.getAllMaterials()).thenReturn(List.of(material));

        mockMvc.perform(get("/api/materials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Material 1"))
                .andExpect(jsonPath("$[0].description").value("Description"))
                .andExpect(jsonPath("$[0].author").value("Author 1"));
    }

    @Test
    void testGetMaterialById() throws Exception {
        when(materialService.getMaterialById(1L)).thenReturn(Optional.of(material));

        mockMvc.perform(get("/api/materials/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Material 1"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    void testCreateMaterial() throws Exception {
        when(materialService.createMaterial(material)).thenReturn(material);

        mockMvc.perform(post("/api/materials")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(material)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Material 1"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    void testUpdateMaterial() throws Exception {
        when(materialService.updateMaterial(1L, material)).thenReturn(material);

        mockMvc.perform(put("/api/materials/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(material)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Material 1"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    void testDeleteMaterial() throws Exception {
        when(materialService.deleteMaterial(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/materials/1"))
                .andExpect(status().isNoContent());
    }
}
