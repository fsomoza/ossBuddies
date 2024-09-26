package com.zombiekid.beginner_oss.shared.infrastracture.controllers;

import com.zombiekid.beginner_oss.shared.infrastracture.authentication.UserService;
import com.zombiekid.beginner_oss.user.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Protected resource
    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    // Admin resource
    @GetMapping("/admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }
}
