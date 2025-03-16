package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.MaterialDTO;
import com.StudyHub.StudyHub.model.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    MaterialDTO toDto(Material material);
    Material toEntity(MaterialDTO materialDTO);
}
