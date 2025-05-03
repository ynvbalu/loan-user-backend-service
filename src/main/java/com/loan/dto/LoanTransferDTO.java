package com.loan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoanTransferDTO {
    @NotBlank(message = "From username is required")
    private String fromUsername;

    @NotBlank(message = "To username is required")
    private String toUsername;
} 