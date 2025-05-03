package com.loan.dto;

import com.loan.entity.Loan.LoanStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanDTO {
    private Long id;
    
    @NotBlank(message = "Loan number is required")
    private String loanNumber;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
    
    @NotNull(message = "Interest rate is required")
    @Positive(message = "Interest rate must be positive")
    @Max(value = 100, message = "Interest rate cannot exceed 100%")
    private BigDecimal interestRate;
    
    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    @Max(value = 360, message = "Term cannot exceed 360 months")
    private Integer term;
    
    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;
    
    @NotNull(message = "Status is required")
    private LoanStatus status;
    
    @NotNull(message = "User ID is required")
    private Long userId;

    // User details
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean oooStatus;
} 