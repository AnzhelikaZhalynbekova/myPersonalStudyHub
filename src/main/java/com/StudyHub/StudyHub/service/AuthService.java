package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.dto.auth.UserRegistrationDTO;
import com.StudyHub.StudyHub.dto.auth.UserResponseDTO;
import com.StudyHub.StudyHub.model.User;
import com.StudyHub.StudyHub.mapper.UserMapper;
import com.StudyHub.StudyHub.repository.UserRepository;
import com.StudyHub.StudyHub.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil; // предполагается, что у тебя есть этот класс для работы с JWT

    public UserResponseDTO register(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        user.setRoles(roles);

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    public String login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        String username = authentication.getName();
        return jwtUtil.generateToken(username);
    }
}
