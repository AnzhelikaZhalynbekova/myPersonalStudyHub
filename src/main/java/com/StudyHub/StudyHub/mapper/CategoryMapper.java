package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.CategoryDTO;
import com.StudyHub.StudyHub.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category category);
    Category toEntity(CategoryDTO categoryDTO);
}