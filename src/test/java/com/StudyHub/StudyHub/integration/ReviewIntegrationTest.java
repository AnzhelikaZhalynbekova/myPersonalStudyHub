package com.StudyHub.StudyHub.integration;

import com.StudyHub.StudyHub.model.Material;
import com.StudyHub.StudyHub.model.Review;
import com.StudyHub.StudyHub.repository.MaterialRepository;
import com.StudyHub.StudyHub.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Material material;
    private Review review;

    @BeforeEach
    void setUp() {
        material = new Material("Test Material", "Test Description", "Test Author", "http://example.com");
        materialRepository.save(material);
        review = new Review("TestUser", "Great material!", 5, material);
        reviewRepository.save(review);
    }

    @Test
    void testCreateReview() throws Exception {
        Review newReview = new Review("NewUser", "Amazing content!", 4, material);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reviews")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newReview)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("NewUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Amazing content!"));
    }

    @Test
    void testGetReviewById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reviews/{id}", review.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("TestUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Great material!"));
    }

    @Test
    void testUpdateReview() throws Exception {
        review.setContent("Updated review content!");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/reviews/{id}", review.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Updated review content!"));
    }

    @Test
    void testDeleteReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/reviews/{id}", review.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

