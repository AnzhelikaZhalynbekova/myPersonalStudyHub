package com.StudyHub.StudyHub.service.impl;

import com.StudyHub.StudyHub.model.User;
import com.StudyHub.StudyHub.repository.UserRepository;
import com.StudyHub.StudyHub.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public void enableUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        userOpt.ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
    }
}

