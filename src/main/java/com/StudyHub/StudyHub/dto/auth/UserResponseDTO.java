package com.StudyHub.StudyHub.dto.auth;

import java.util.Set;

public class UserResponseDTO {

    private Long id;
    private String email;
    private String fullName;
    private Set<String> roles;

    // Конструкторы
    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String email, String fullName, String role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return roles.toString();
    }

    public void setRole(String role) {
        this.roles = roles;
    }
}
