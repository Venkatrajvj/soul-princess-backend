package com.soulprincess.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return "Email already registered";
        }

        userRepository.save(user);
        return "User registered successfully";
    }

    // Login user
    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest) {

        Optional<User> existing = userRepository.findByEmail(loginRequest.getEmail());

        if (existing.isEmpty()) {
            return "User not found";
        }

        User user = existing.get();

        if (user.getPassword().equals(loginRequest.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid password";
        }
    }

    // Get all users (for testing/admin)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}