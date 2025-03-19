package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.Material;
import java.util.List;
import java.util.Optional;

public interface MaterialService {
    Material createMaterial(Material material);
    Optional<Material> getMaterialById(Long id);
    List<Material> getAllMaterials();
    Material updateMaterial(Long id, Material material);
    void deleteMaterial(Long id);
}

