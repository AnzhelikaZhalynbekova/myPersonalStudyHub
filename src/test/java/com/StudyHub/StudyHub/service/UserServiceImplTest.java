package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.User;
import com.StudyHub.StudyHub.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testPasswordEncoderInjected() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");

        User savedUser = userService.registerUser(user);

        // Проверяем, что пароль в базе не plain text
        assertNotEquals("plainPassword", savedUser.getPassword());
        assertTrue(savedUser.getPassword().startsWith("$2a$")); // bcrypt-хеш начинается с $2a$, $2b$ или $2y$
    }
}
