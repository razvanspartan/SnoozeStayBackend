package com.example.snoozestaybackend2.api.controller;
import com.example.snoozestaybackend2.api.model.AuthenticationRequest;
import com.example.snoozestaybackend2.api.model.User;
import com.example.snoozestaybackend2.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/usersRegister")
    public ResponseEntity<Map<String, Integer>> addUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        Map<String, Integer> response = new HashMap<>();
        response.put("id", savedUser.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/usersAuth")
    public ResponseEntity<Map<String,Integer>> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();
        User user = userService.findUserByEmail(email);

        if(user == null || !password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Map<String, Integer> response = new HashMap<>();
        response.put("id", user.getId());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/users/{reviewId}")
    public ResponseEntity<User> getUserByReviewId(@PathVariable int reviewId) {
        User user = userService.getUserByReviewId(reviewId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
