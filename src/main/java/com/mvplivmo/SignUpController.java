//package com.mvplivmo;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/signup")
//@CrossOrigin(origins = "http://localhost:8081/")
//public class SignUpController {
//
//    @Autowired
//    private SignUpService signUpService;
//
//    // Endpoint to handle user registration
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody SignUp signUp) {
//        try {
//            SignUp savedUser = signUpService.registerUser(signUp);
//            return ResponseEntity.ok(savedUser);
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(400).body(e.getMessage()); // Return error if user already exists
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error occurred during registration.");
//        }
//    }
//
//    // Optional: Endpoint to find a user by email
//    @GetMapping("/user/{email}")
//    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
//        Optional<SignUp> user = signUpService.findUserByEmail(email);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        } else {
//            return ResponseEntity.status(404).body("User not found.");
//        }
//    }
//}



package com.mvplivmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins = "http://localhost:8081/")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    // Endpoint to handle user registration
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody SignUp signUp) {
        try {
            SignUp savedUser = signUpService.registerUser(signUp);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage()); // Return error if user already exists
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred during registration.");
        }
    }

    // Endpoint to find a user by email
    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<SignUp> user = signUpService.findUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    // Endpoint to find a user by mobile number (optional)
    @GetMapping("/api/signup")
    public ResponseEntity<?> getUserByMobile(@PathVariable String mobileNumber) {
        Optional<SignUp> user = signUpService.findUserByMobile(mobileNumber);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    // Endpoint to delete a user by email
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        try {
            signUpService.deleteUserByEmail(email);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Return error if user does not exist
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred during deletion.");
        }
    }
}