package com.elypia.backend.controllers;

import com.elypia.backend.database.entities.User;
import com.elypia.backend.database.entities.VerificationToken;
import com.elypia.backend.database.repositories.UserRepository;
import com.elypia.backend.database.repositories.VerificationRepository;
import com.elypia.backend.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private UserRepository userRepo;
    private VerificationRepository verifyRepo;

    @Autowired
    public UserController(UserRepository userRepo, VerificationRepository verifyRepo) {
        this.userRepo = userRepo;
        this.verifyRepo = verifyRepo;
    }

    @GetMapping
    public User getUser(@RequestParam int userId) {
        return userRepo.findById(userId);
    }

    @GetMapping("/exists")
    public ResponseEntity<String> getUser(@RequestParam String email) {
        if (userRepo.findByEmail(email) != null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else if (email.toLowerCase().contains("teapot"))
            return new ResponseEntity<>("Just tip me over and pour me out!", HttpStatus.I_AM_A_TEAPOT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Verify a user account to that they are able to login.
     * Failure to do so may result in account deletion.

     * @param token The verification token sent to the user.
     * @return The HTTP response for the user.
     */
    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verify(@PathVariable("token") String token) {
        VerificationToken verificationToken = verifyRepo.findByToken(token);

        if (verificationToken == null)
            return new ResponseEntity<>("Verification token does not exist.", HttpStatus.NOT_FOUND);

        User user = verificationToken.getUser();
        verifyRepo.delete(verificationToken);

        if (user.isVerified())
            return new ResponseEntity<>("You have already verified your account.", HttpStatus.BAD_REQUEST);

        if (new Date().after(verificationToken.getExpiry()))
            return new ResponseEntity<>("This verification token has expired, please generate another and try again.", HttpStatus.NOT_FOUND);

        user.setVerified(true);
        userRepo.save(user);
        return new ResponseEntity<>("You have succesfully verified your account.", HttpStatus.OK);
    }

    @PostMapping("/register")
    public User register(@RequestBody @Valid RegisterForm form) {
        User user = new User(form);
        userRepo.save(user);
        return user;
    }
}
