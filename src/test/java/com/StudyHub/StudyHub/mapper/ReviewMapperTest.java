package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.ReviewDTO;
import com.StudyHub.StudyHub.model.Review;
import com.StudyHub.StudyHub.model.Material;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class ReviewMapperTest {

    private final ReviewMapper reviewMapper = Mappers.getMapper(ReviewMapper.class);

    @Test
    void testToDto() {
        Material material = new Material("Spring Boot Guide", "Learn Spring Boot in detail", "Mr.Zhavlon", "https://example.com/spring-boot-guide");
        Review review = new Review("Aikan", "Great guide for Spring Boot", 5, material);

        ReviewDTO reviewDTO = reviewMapper.toDto(review);

        assertEquals(review.getUsername(), reviewDTO.getUsername());
        assertEquals(review.getText(), reviewDTO.getContent());
        assertEquals(review.getRating(), reviewDTO.getRating());
        assertEquals(review.getMaterial().getId(), reviewDTO.getMaterialId());
    }

    @Test
    void testToEntity() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUsername("Aikan");
        reviewDTO.setContent("Great guide for Spring Boot");
        reviewDTO.setRating(5);
        reviewDTO.setMaterialId(1L);

        Review review = reviewMapper.toEntity(reviewDTO);

        assertEquals(reviewDTO.getUsername(), review.getUsername());
        assertEquals(reviewDTO.getContent(), review.getText());
        assertEquals(reviewDTO.getRating(), review.getRating());
    }
}

