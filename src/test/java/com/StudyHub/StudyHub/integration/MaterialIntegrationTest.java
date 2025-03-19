package com.StudyHub.StudyHub.integration;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import com.StudyHub.StudyHub.service.MaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MaterialIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ObjectMapper objectMapper;

    private Material material;

    @BeforeEach
    void setUp() {
        material = new Material("Test Material", "Test Description", "Test Author", "http://example.com");
        materialRepository.save(material);  // Ensure material is saved before each test
    }

    @Test
    void testCreateMaterial() throws Exception {
        Material newMaterial = new Material("New Material", "New Description", "New Author", "http://newurl.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/materials")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newMaterial)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Material"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("New Description"));
    }

    @Test
    void testGetMaterialById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/materials/{id}", material.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Material"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Test Description"));
    }

    @Test
    void testUpdateMaterial() throws Exception {
        material.setTitle("Updated Title");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/materials/{id}", material.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(material)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteMaterial() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/materials/{id}", material.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
