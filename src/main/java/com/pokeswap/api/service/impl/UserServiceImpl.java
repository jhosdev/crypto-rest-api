package com.pokeswap.api.service.impl;

import com.pokeswap.api.model.User;
import com.pokeswap.api.repository.UserRepository;
import com.pokeswap.api.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmailAndIsActiveIsTrue(email);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);
        if (userToUpdate != null) {
            BeanUtils.copyProperties(user, userToUpdate, "id");
            return userRepository.save(userToUpdate);
        }
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        userRepository.updateUserStatus(id, false);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailAndIsActiveIsTrue(email);
    }
}
