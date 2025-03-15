package com.StudyHub.StudyHub.bootstrap;

import com.StudyHub.StudyHub.model.Category;
import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.model.Review;
import com.StudyHub.StudyHub.repository.CategoryRepository;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import com.StudyHub.StudyHub.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Categories
        Category category1 = new Category("Programming");
        Category category2 = new Category("Mathematics");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // Create Materials
        Material material1 = new Material("Spring Boot Guide", "Learn Spring Boot in detail", "Mr.Zhavlon", "https://example.com/spring-boot-guide");
        material1.setCategory(category1);
        materialRepository.save(material1);

        Material material2 = new Material("Calculus Basics", "Introduction to Calculus", "Mr.Chebsi", "https://example.com/calculus-basics");
        material2.setCategory(category2);
        materialRepository.save(material2);

        // Create Reviews
        Review review1 = new Review("Aikan", "Great guide for Spring Boot", 5, material1);
        Review review2 = new Review("Bermet", "Helpful, but a bit difficult", 3, material1);
        reviewRepository.save(review1);
        reviewRepository.save(review2);

        Review review3 = new Review("Sasha", "A good intro to calculus", 4, material2);
        reviewRepository.save(review3);
    }
}
