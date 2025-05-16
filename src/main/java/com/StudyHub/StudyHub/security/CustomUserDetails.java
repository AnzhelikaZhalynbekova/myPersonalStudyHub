package com.StudyHub.StudyHub.security;

import com.StudyHub.StudyHub.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Преобразуем роли из Set<String> в коллекцию GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> roles = user.getRoles();
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Ниже базовые методы, которые обычно возвращают true или берут из пользователя

    @Override
    public boolean isAccountNonExpired() {
        return true; // можно усложнить логику
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // например, можно добавить блокировки
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // можно реализовать логику истечения пароля
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled(); // используем поле enabled из User
    }
}

