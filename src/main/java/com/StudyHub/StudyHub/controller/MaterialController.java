package com.StudyHub.StudyHub.controller;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    // GET all materials
    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialService.getAllMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    // GET a single material by ID
    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id) {
        Optional<Material> material = materialService.getMaterialById(id);
        return material.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new material
    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        Material createdMaterial = materialService.createMaterial(material);
        return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED);
    }

    // PUT to update an existing material
    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Long id, @RequestBody Material material) {
        Material updatedMaterial = materialService.updateMaterial(id, material);
        return updatedMaterial != null ? ResponseEntity.ok(updatedMaterial)
                : ResponseEntity.notFound().build();
    }

    // DELETE a material by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        if (materialService.deleteMaterial(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

