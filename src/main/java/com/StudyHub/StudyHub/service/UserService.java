package com.StudyHub.StudyHub.service;

import com.StudyHub.StudyHub.model.User;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByUsername(String username);
    void enableUser(Long id);
}
