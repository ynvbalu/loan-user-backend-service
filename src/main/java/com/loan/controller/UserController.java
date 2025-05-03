package com.loan.controller;

import com.loan.dto.UserDTO;
import com.loan.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    @Operation(summary = "Get total count of users")
    public ResponseEntity<Long> countUsers() {
        return ResponseEntity.ok(userService.countUsers());
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if user exists by ID")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.existsById(id));
    }

    @GetMapping("/exists/username/{username}")
    @Operation(summary = "Check if user exists by username")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.existsByUsername(username));
    }

    @GetMapping("/{id}/ooo")
    @Operation(summary = "Check if user is OOO by ID")
    public ResponseEntity<Boolean> isUserOOO(@PathVariable Long id) {
        return ResponseEntity.ok(userService.isUserOOO(id));
    }

    @GetMapping("/username/{username}/ooo")
    @Operation(summary = "Check if user is OOO by username")
    public ResponseEntity<Boolean> isUserOOOByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.isUserOOOByUsername(username));
    }
} 