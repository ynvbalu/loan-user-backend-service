package com.loan.dto;

import com.loan.entity.Loan.LoanStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private BigDecimal interestRate;
    
    @NotNull(message = "Term is required")
    @Positive(message = "Term must be positive")
    private Integer term;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "Status is required")
    private LoanStatus status;
    
    @NotNull(message = "User ID is required")
    private Long userId;
} 