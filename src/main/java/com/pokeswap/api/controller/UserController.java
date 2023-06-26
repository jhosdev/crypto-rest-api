package com.pokeswap.api.controller;

import com.pokeswap.api.model.User;
import com.pokeswap.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokeswap/v1")
public class UserController {

    private final UserService userService;
    

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //URl: http://localhost:8080/api/pokeswap/v1/users
    //Method: GET
    //Description: Get all users
    //Response: List of users
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/users/{id}
    //Method: GET
    //Description: Get user by id
    //Response: User
    @Transactional(readOnly = true)
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/users
    //Method: POST
    //Description: Create user
    //Response: User
    //Request body: User
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/users/{id}
    //Method: PUT
    //Description: Update user
    //Response: User
    //Request body: User
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(id, user), HttpStatus.OK);
    }

    //URl: http://localhost:8080/api/pokeswap/v1/users/{id}
    //Method: DELETE
    //Description: Delete user
    //Response: User
    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<User>(userService.deleteUser(id), HttpStatus.OK);
    }

}
