package com.StudyHub.StudyHub.repositoriesTest;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MaterialRepositoryTest {

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    public void shouldSaveMaterial() {
        Material material = new Material("Java Basics", "Introduction to Java", "Mr.Dim", "http://example.com");
        Material savedMaterial = materialRepository.save(material);

        assertThat(savedMaterial).isNotNull();
        assertThat(savedMaterial.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindMaterialById() {
        Material material = new Material("Spring Framework", "Learning Spring", "Mr.Zhavlon", "http://example.com");
        Material savedMaterial = materialRepository.save(material);

        Material foundMaterial = materialRepository.findById(savedMaterial.getId()).orElse(null);

        assertThat(foundMaterial).isNotNull();
        assertThat(foundMaterial.getId()).isEqualTo(savedMaterial.getId());
        assertThat(foundMaterial.getTitle()).isEqualTo(savedMaterial.getTitle());
    }

    @Test
    public void shouldDeleteMaterial() {
        Material material = new Material("Database Basics", "Learn SQL", "Mr.Adilet", "http://example.com");
        Material savedMaterial = materialRepository.save(material);

        materialRepository.delete(savedMaterial);

        Material deletedMaterial = materialRepository.findById(savedMaterial.getId()).orElse(null);
        assertThat(deletedMaterial).isNull();
    }
}
