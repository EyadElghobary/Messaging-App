package com.example.demo.controllers;

// import com.example.demo.models.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {
//     @Autowired
//     UserRepository userRepository;

//     @GetMapping("/me")
//     public ResponseEntity<?> me(@AuthenticationPrincipal User user) {
//         return ResponseEntity.ok(user);
//     }

//     @GetMapping("/{id}")
//     @PreAuthorize("#user.id == #id")
//     public ResponseEntity<?> me(@AuthenticationPrincipal User user, @PathVariable String id) {
//         return ResponseEntity.ok(userRepository.findById(id));
//     }

// @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody User user) {
//         System.out.println(user.getPassword());
//         System.out.println("-----------------");
//         try {
//             // Check if username already exists
//             if (userRepository.findByUsername(user.getUsername()).isPresent()) {
//                 return ResponseEntity.badRequest().body("Username already exists");
//             }
//             // Check if email already exists
//             if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//                 return ResponseEntity.badRequest().body("Email already exists");
//             }

//             // Save new user
//             userRepository.save(user);
//             return ResponseEntity.ok("User registered successfully");
//         } catch (Exception e) {
//             return ResponseEntity.internalServerError().body("Error registering user");
//         }
//     }


//     @PostMapping("/login")
//     public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
//         return userRepository.findByUsername(loginUser.getUsername())
//             .map(user -> user.getPassword().equals(loginUser.getPassword()) ? 
//                 ResponseEntity.ok("User logged in successfully") : 
//                 ResponseEntity.badRequest().body("Invalid credentials"))
//             .orElse(ResponseEntity.badRequest().body("User not found"));
//     }
// }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder

    
    
}