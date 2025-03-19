package com.StudyHub.StudyHub.service.impl;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import com.StudyHub.StudyHub.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Material updateMaterial(Long id, Material material) {
        if (!materialRepository.existsById(id)) {
            throw new IllegalArgumentException("Material not found with id: " + id);
        }
        material.setId(id);
        return materialRepository.save(material);
    }

    @Override
    public boolean deleteMaterial(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new IllegalArgumentException("Material not found with id: " + id);
        }
        materialRepository.deleteById(id);
        return false;
    }
}
