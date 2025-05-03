package com.loan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class LoanTransferDTO {
    @NotBlank(message = "From username is required")
    private String fromUsername;

    @NotBlank(message = "To username is required")
    private String toUsername;
    
    private List<Long> loanIds; // Optional list of loan IDs to transfer. If null, transfer all loans
} 