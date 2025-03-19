package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.MaterialDTO;
import com.StudyHub.StudyHub.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    @Mapping(source = "category.id", target = "categoryId")
    MaterialDTO toDto(Material material);

    @Mapping(source = "categoryId", target = "category.id")
    Material toEntity(MaterialDTO materialDTO);
}
