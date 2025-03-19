package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import com.StudyHub.StudyHub.service.impl.MaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MaterialServiceImplTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialServiceImpl materialService;

    private Material material;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        material = new Material("Title", "Description", "Author", "fileUrl");
    }

    @Test
    void testCreateMaterial() {
        when(materialRepository.save(material)).thenReturn(material);

        Material createdMaterial = materialService.createMaterial(material);

        assertNotNull(createdMaterial);
        assertEquals("Title", createdMaterial.getTitle());
    }

    @Test
    void testGetMaterialById() {
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        Optional<Material> foundMaterial = materialService.getMaterialById(1L);

        assertTrue(foundMaterial.isPresent());
        assertEquals("Title", foundMaterial.get().getTitle());
    }

    @Test
    void testUpdateMaterial() {
        when(materialRepository.existsById(1L)).thenReturn(true);
        when(materialRepository.save(material)).thenReturn(material);

        Material updatedMaterial = materialService.updateMaterial(1L, material);

        assertEquals("Title", updatedMaterial.getTitle());
    }

    @Test
    void testDeleteMaterial() {
        when(materialRepository.existsById(1L)).thenReturn(true);

        materialService.deleteMaterial(1L);

        verify(materialRepository, times(1)).deleteById(1L);
    }
}
