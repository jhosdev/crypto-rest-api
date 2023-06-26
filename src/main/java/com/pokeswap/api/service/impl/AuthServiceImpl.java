package com.pokeswap.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pokeswap.api.dto.LoginDTO;
import com.pokeswap.api.dto.UserDTO;
import com.pokeswap.api.model.User;
import com.pokeswap.api.repository.UserRepository;
import com.pokeswap.api.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;



    private final ModelMapper modelMapper = new ModelMapper();

    private String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
        String jwt = JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withClaim("password", user.getPassword())
                .withClaim("name", user.getName())
                .withClaim("phone", user.getPhone())
                .withClaim("country", user.getCountry())
                .withClaim("address", user.getAddress())
                .withClaim("balance", user.getBalance())
                .withClaim("createdAt", user.getCreatedAt().toString())
                .withClaim("isActive", user.getIsActive())
                .sign(algorithm);
        //System.out.println("JWT: " + jwt);
        return jwt;
    }

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean userExists(String email) {
        return userRepository.existsByEmailAndIsActiveIsTrue(email);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByEmailAndIsActiveIsTrue(loginDTO.getEmail());
        String hashedPassword = user.getPassword();
        String providedPassword = loginDTO.getPassword();

        if (passwordEncoder.matches(providedPassword, hashedPassword)) {
            System.out.println("Password matches");
            return generateToken(user);
        }
        System.out.println("Password does not match");
        return null;
    }

    @Override
    public Boolean register(UserDTO userDTO) {
        try {
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(hashedPassword);
            User user = modelMapper.map(userDTO, User.class);
            System.out.println(user);
            User saveduser = userRepository.save(user);
            System.out.println(saveduser);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
