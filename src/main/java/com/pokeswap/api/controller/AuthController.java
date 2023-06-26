package com.pokeswap.api.controller;

import com.pokeswap.api.dto.LoginDTO;
import com.pokeswap.api.dto.UserDTO;
import com.pokeswap.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //URL: http://localhost:8080/api/pokeswap/v1/auth/login
    //Method: POST
    //Description: Login
    //Response: User
    @Transactional
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String res = authService.login(loginDTO);
        if (res == null || res.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        return ResponseEntity.ok(res);
    }

    //URL: http://localhost:8080/api/pokeswap/v1/auth/register
    //Method: POST
    //Description: Register
    //Response: User
    @Transactional
    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        Boolean resp = authService.register(userDTO);
        if (!resp) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        return ResponseEntity.ok("User created successfully");
    }
}
