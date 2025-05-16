package com.StudyHub.StudyHub.controller;

import com.StudyHub.StudyHub.model.User;
import com.StudyHub.StudyHub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Внедрение сервиса через конструктор
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Регистрация пользователя
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Получение пользователя по username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> userOpt = userService.findByUsername(username);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Активация пользователя (например, по id)
    @PostMapping("/enable/{id}")
    public ResponseEntity<Void> enableUser(@PathVariable Long id) {
        userService.enableUser(id);
        return ResponseEntity.ok().build();
    }
}

