package com.antonyanapps.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public @NotEmpty(message = "First name must be required") @NotBlank(message = "First name must be required") String getFirstName() {
        return firstName;
    }

    public @NotEmpty(message = "Last name must be required") @NotBlank(message = "Last name must be required") String getLastName() {
        return lastName;
    }

    public @NotEmpty(message = "Username must be required") @NotBlank(message = "Username must be required") String getUsername() {
        return username;
    }

    public @Email(message = "Email structure is invalid") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Password must be required") @NotEmpty(message = "Password must be required") @Size(min = 8, max = 16, message = "Password length must be between 8 and 16 characters") String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(@NotEmpty(message = "First name must be required") @NotBlank(message = "First name must be required") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotEmpty(message = "Last name must be required") @NotBlank(message = "Last name must be required") String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(@NotEmpty(message = "Username must be required") @NotBlank(message = "Username must be required") String username) {
        this.username = username;
    }

    public void setEmail(@Email(message = "Email structure is invalid") String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank(message = "Password must be required") @NotEmpty(message = "Password must be required") @Size(min = 8, max = 16, message = "Password length must be between 8 and 16 characters") String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, email, password);
    }
}
