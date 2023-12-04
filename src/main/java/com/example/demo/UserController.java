package com.example.demo;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user data");
        }

        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok("User created successfully with ID: " + savedUser.getId());
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    // UserController.java
    @PutMapping("/{googleId}/updateHandle")
    public ResponseEntity<String> updateHandle(@PathVariable String googleId, @RequestParam String newHandle) {
        try {
            List<User> users = userRepository.findByGoogleId(googleId);
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            User user = users.get(0);
            user.setGameHandle(newHandle);
            userRepository.save(user);
            return ResponseEntity.ok("User handle updated successfully for Google ID: " + googleId);
        } catch (Exception e) {
            System.out.println("Error updating user handle: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user handle: " + e.getMessage());
        }
    }




}
