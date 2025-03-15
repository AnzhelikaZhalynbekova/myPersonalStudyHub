package com.StudyHub.StudyHub.repositoriesTest;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.model.Review;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import com.StudyHub.StudyHub.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Test
    public void shouldSaveReview() {
        Material material = new Material("Java Basics", "Introduction to Java", "Mr.Dim", "http://example.com");
        materialRepository.save(material);

        Review review = new Review("Aizhan", "Great material!", 5, material);
        Review savedReview = reviewRepository.save(review);

        assertThat(savedReview).isNotNull();
        assertThat(savedReview.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindReviewById() {
        Material material = new Material("Spring Framework", "Learning Spring", "Mr.Zhavlon", "http://example.com");
        materialRepository.save(material);

        Review review = new Review("Bek", "Very helpful", 4, material);
        Review savedReview = reviewRepository.save(review);

        Review foundReview = reviewRepository.findById(savedReview.getId()).orElse(null);

        assertThat(foundReview).isNotNull();
        assertThat(foundReview.getId()).isEqualTo(savedReview.getId());
        assertThat(foundReview.getMaterial().getTitle()).isEqualTo(savedReview.getMaterial().getTitle());
    }

    @Test
    public void shouldDeleteReview() {
        Material material = new Material("Database Basics", "Learn SQL", "Mr.Adilet", "http://example.com");
        materialRepository.save(material);

        Review review = new Review("Alym", "Good content", 3, material);
        Review savedReview = reviewRepository.save(review);

        reviewRepository.delete(savedReview);

        Review deletedReview = reviewRepository.findById(savedReview.getId()).orElse(null);
        assertThat(deletedReview).isNull();
    }
}
