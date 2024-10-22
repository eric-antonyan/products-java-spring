package com.antonyanapps.products.service;

import com.antonyanapps.products.CustomErrorResponse;
import com.antonyanapps.products.model.User;
import com.antonyanapps.products.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public ResponseEntity<?> create(User user) {
        Optional<User> existingEmailUser = userRepository.findByEmail(user.getEmail());
        Optional<User> existingUsernameUser = userRepository.findByUsername(user.getUsername());

        if (existingEmailUser.isPresent() && existingUsernameUser.isPresent()) {
            return createErrorResponse("Email and username already in use", HttpStatus.CONFLICT);
        } else if (existingEmailUser.isPresent()) {
            return createErrorResponse("Email (" + user.getEmail() + ") already in use", HttpStatus.CONFLICT);
        } else if (existingUsernameUser.isPresent()) {
            return createErrorResponse("Username (" + user.getUsername() + ") already in use", HttpStatus.CONFLICT);
        }

        User newUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    public ResponseEntity<?> getUserById(Long id) {
        return userRepository.findById(id)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> createErrorResponse(
                        "User with id " + id + " not found in database",
                        HttpStatus.NOT_FOUND
                ));
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userRepository.findAll());
    }

    private ResponseEntity<CustomErrorResponse> createErrorResponse(String message, HttpStatus status) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(message, status);
        return ResponseEntity.status(status).body(customErrorResponse);
    }
}
