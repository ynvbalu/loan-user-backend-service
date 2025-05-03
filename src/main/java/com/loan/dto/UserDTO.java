package com.loan.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class UserDTO {
    private Long id;
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    private String phoneNumber;
    
    @NotNull(message = "OOO status is required")
    private Boolean oooStatus;
} 