package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.ReviewDTO;
import com.StudyHub.StudyHub.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toDto(Review review);
    Review toEntity(ReviewDTO reviewDTO);
}
