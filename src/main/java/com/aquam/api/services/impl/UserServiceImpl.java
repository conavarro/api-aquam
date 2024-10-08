package com.aquam.api.services.impl;

import com.aquam.api.entities.User;
import com.aquam.api.repositories.UserRepository;
import com.aquam.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return this.userRepository.findById(id).orElseThrow();
    }
}
