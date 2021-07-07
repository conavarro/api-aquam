package com.aquam.api.service;

import com.aquam.api.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(long id);

    void deleteUser(User user);

    void updateUsers(List<User> users);

    void createUser(User user);

    List<User> getAllUsers();
}
