package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.model.Review;
import com.StudyHub.StudyHub.repository.ReviewRepository;
import com.StudyHub.StudyHub.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        review = new Review("user1", "Great material!", 5, new Material());
    }


    @Test
    void testCreateReview() {
        when(reviewRepository.save(review)).thenReturn(review);

        Review createdReview = reviewService.createReview(review);

        assertNotNull(createdReview);
        assertEquals("Great material!", createdReview.getContent());
    }

    @Test
    void testGetReviewById() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Optional<Review> foundReview = reviewService.getReviewById(1L);

        // Проверка, что объект существует
        assertTrue(foundReview.isPresent());

        // Проверка текста в найденном обзоре
        assertEquals("Great material!", foundReview.get().getContent());
    }


    @Test
    void testUpdateReview() {
        when(reviewRepository.existsById(1L)).thenReturn(true);
        when(reviewRepository.save(review)).thenReturn(review);

        Review updatedReview = reviewService.updateReview(1L, review);

        assertEquals("Great material!", updatedReview.getContent());
    }

    @Test
    void testDeleteReview() {
        when(reviewRepository.existsById(1L)).thenReturn(true);

        reviewService.deleteReview(1L);

        verify(reviewRepository, times(1)).deleteById(1L);
    }
}
