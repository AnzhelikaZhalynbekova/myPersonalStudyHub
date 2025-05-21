package com.StudyHub.StudyHub.mapper;

import com.StudyHub.StudyHub.dto.auth.UserRegistrationDTO;
import com.StudyHub.StudyHub.dto.auth.UserResponseDTO;
import com.StudyHub.StudyHub.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) return null;

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),

               // user.isEmailVerified()
        );
    }

    public User toEntity(UserRegistrationDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Будь внимательна! Не шифрованный пароль — шифруй в сервисе
        //user.setEmailVerified(false);
        return user;
    }
}
