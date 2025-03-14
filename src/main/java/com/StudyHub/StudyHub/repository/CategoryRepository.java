package com.StudyHub.StudyHub.repository;

import com.StudyHub.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    // Вы можете добавить свои методы для поиска данных, если нужно
    // Например, поиск по категории
    List<Material> findByCategoryId(Long categoryId);
}
