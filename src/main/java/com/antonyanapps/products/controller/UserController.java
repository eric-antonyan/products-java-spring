package com.antonyanapps.products.controller;

import com.antonyanapps.products.CustomErrorResponse;
import com.antonyanapps.products.model.User;
import com.antonyanapps.products.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            bindingResult.getAllErrors()
                                    .stream()
                                    .map(ObjectError::getDefaultMessage)
                                    .collect(Collectors.toList())  // Collect errors into a list
                    );
        }
        return userService.create(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@Valid @PathVariable String id) {
        Long parsedId;

        try {
            parsedId = Long.parseLong(id);
        } catch (NumberFormatException ex) {
            return createErrorResponse(
                    "Invalid ID Format. ID must be number",
                    HttpStatus.BAD_REQUEST
            );
        }

        return this.userService.getUserById(parsedId);
    }

    private ResponseEntity<CustomErrorResponse> createErrorResponse(String message, HttpStatus status) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(message, status);
        return ResponseEntity.status(status).body(customErrorResponse);
    }
}
