package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.auth.UserRegistrationDTO;
import com.StudyHub.StudyHub.dto.auth.UserResponseDTO;
import com.StudyHub.StudyHub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // регистрация: из DTO в Entity (пароль будет установлен вручную в AuthService)
    User toEntity(UserRegistrationDTO dto);

    // ответ клиенту: из Entity в DTO
    //@Mapping(target = "role", expression = "java(user.getRole().name())")
    UserResponseDTO toDTO(User user);
}
