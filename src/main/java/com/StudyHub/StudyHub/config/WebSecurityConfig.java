package com.StudyHub.StudyHub.config;

import com.StudyHub.StudyHub.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // отключаем CSRF для упрощения (потом можно включить с настройками)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // регистрация и доки открыты
                        .anyRequest().authenticated() // остальные требуют авторизации
                )
                .userDetailsService(userDetailsService)
                .httpBasic(basic -> {}); // базовая HTTP аутентификация (заменим на JWT позже)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // шифрование паролей
    }

    // Для получения AuthenticationManager (нужно для аутентификации)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
