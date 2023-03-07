package com.example.bankdemo.service.impl;

import com.example.bankdemo.entity.User;
import com.example.bankdemo.repository.UserRepository;
import com.example.bankdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
