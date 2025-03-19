package com.StudyHub.StudyHub.controller;

import com.StudyHub.StudyHub.model.Review;
import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private MockMvc mockMvc;
    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        // Создаем объект Material, который будет передан в конструктор Review
        Material material = new Material();
        material.setTitle("Material 1");
        material.setDescription("Description");
        material.setAuthor("Author");
        material.setFileUrl("https://example.com");

        review = new Review("User1", "Great material!", 5, material);
    }

    @Test
    void testGetAllReviews() throws Exception {
        // Пример заглушки для списка отзывов
        when(reviewService.getAllReviews()).thenReturn(List.of(review));

        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Great material!"))
                .andExpect(jsonPath("$[0].rating").value(5));
    }

    @Test
    void testGetReviewById() throws Exception {
        when(reviewService.getReviewById(1L)).thenReturn(Optional.of(review));

        mockMvc.perform(get("/api/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Great material!"))
                .andExpect(jsonPath("$.rating").value(5));
    }

    @Test
    void testCreateReview() throws Exception {
        when(reviewService.createReview(review)).thenReturn(review);

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(review)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("Great material!"))
                .andExpect(jsonPath("$.rating").value(5));
    }

    @Test
    void testUpdateReview() throws Exception {
        when(reviewService.updateReview(1L, review)).thenReturn(review);

        mockMvc.perform(put("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(review)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Great material!"))
                .andExpect(jsonPath("$.rating").value(5));
    }

    @Test
    void testDeleteReview() throws Exception {
        when(reviewService.deleteReview(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isNoContent());
    }
}
