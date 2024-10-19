package com.antonyanapps.products.controller;

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

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers() {
        return "string";
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
}
