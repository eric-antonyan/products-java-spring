package com.antonyanapps.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name must be required")
    @NotBlank(message = "First name must be required")
    private String firstName;

    @NotEmpty(message = "Last name must be required")
    @NotBlank(message = "Last name must be required")
    private String lastName;

    @NotEmpty(message = "Username must be required")
    @NotBlank(message = "Username must be required")
    @Column(unique = true)
    private String username;

    @Email(message = "Email structure is invalid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password must be required")
    @NotEmpty(message = "Password must be required")
    @Size(min = 8, max = 16, message = "Password length must be between 8 and 16 characters")
    private String password;
}
