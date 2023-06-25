package com.pokeswap.api.service;

import com.pokeswap.api.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean existsByEmail(String email);

    User updateUser(Long id, User user);

    User deleteUser(Long id);

    User findByEmail(String email);
}
