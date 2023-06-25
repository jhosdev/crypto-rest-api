package com.pokeswap.api.service.impl;

import com.pokeswap.api.dto.LoginDTO;
import com.pokeswap.api.dto.LoginResponseDTO;
import com.pokeswap.api.model.User;
import com.pokeswap.api.repository.UserRepository;
import com.pokeswap.api.service.AuthService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //@Value("${jwt.secret}")
    private String jwtSecret;

    private String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        boolean foundUser = userRepository.existsByEmailAndIsActiveIsTrue(loginDTO.getEmail());

        if (foundUser) {
            User user = userRepository.findByEmailAndIsActiveIsTrue(loginDTO.getEmail());
            String hashedPassword = user.getPassword();
            String providedPassword = loginDTO.getPassword();

            if (passwordEncoder.matches(providedPassword, hashedPassword)) {
                String token = generateToken(user.getEmail());

                // Create the login response DTO with the token and success status
                LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                        .token(token)
                        .success(true)
                        .build();

                return loginResponseDTO;
            } else {
                return LoginResponseDTO.builder()
                        .token(null)
                        .success(false)
                        .build();
            }
        }

        return null; // User not found
    }

    @Override
    public String register(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User saveduser = userRepository.save(user);
        if (saveduser != null) {
            return "User created successfully";
        }
        return "User creation failed";
    }


}
