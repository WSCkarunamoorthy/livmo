package com.mvplivmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Optional;

@Service
public class SignUpService {

    @Autowired
    private SignUpRepo signUpRepo; // Assuming you have a MongoDB repository for SignUp
    private List<SignUp> signUp = new ArrayList<>();
	 private AtomicInteger counter = new AtomicInteger(1);
	 
//   Create new SignUp
	 public SignUp createSignUp(SignUp signUp) {
		 signUpRepo.save(signUp);
	        return signUp;
	    }
	 

    // Method to register a new user
    public SignUp registerUser(SignUp signUp) {
        // Optional: Add logic to check if a user with the same email or mobile number already exists
        Optional<SignUp> existingUser = signUpRepo.findById(signUp.getEmail());
       
        if (existingUser.isPresent()) {
            throw new IllegalStateException("User with this email already exists.");
        }

        // Save the new user to the database
        
        return signUpRepo.save(signUp);
    }

    // Method to find a user by email (optional)
    public Optional<SignUp> findUserByEmail(String email) {
        return signUpRepo.findById(email);
    }

public Optional<SignUp> findUserByMobile(String mobileNumber) {
        return signUpRepo.findById(mobileNumber);
    }

    // Method to delete a user by email
    public void deleteUserByEmail(String email) {
        Optional<SignUp> user = signUpRepo.findById(email);
        if (user.isPresent()) {
            signUpRepo.delete(user.get());
        } else {
            throw new IllegalStateException("User with this email does not exist.");
        }
    }
}

