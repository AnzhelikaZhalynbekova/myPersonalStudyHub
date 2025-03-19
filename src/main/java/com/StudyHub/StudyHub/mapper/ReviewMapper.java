package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.ReviewDTO;
import com.StudyHub.StudyHub.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "content", target = "content")
    @Mapping(source = "material.id", target = "materialId")
    ReviewDTO toDto(Review review);

    @Mapping(source = "content", target = "content")
    @Mapping(source = "materialId", target = "material.id")
    Review toEntity(ReviewDTO reviewDTO);
}
