package com.loan.controller;

import com.loan.dto.LoanDTO;
import com.loan.dto.LoanTransferDTO;
import com.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.loan.entity.Loan.LoanStatus;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Loan Management", description = "APIs for managing loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    @Operation(summary = "Create a new loan")
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.createLoan(loanDTO));
    }

    @GetMapping
    @Operation(summary = "Get all loans")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a loan by ID")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all loans for a specific user")
    public ResponseEntity<List<LoanDTO>> getLoansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUserId(userId));
    }

    @GetMapping("/user/{userId}/has-loans")
    @Operation(summary = "Check if a user has any loans")
    public ResponseEntity<Map<String, Object>> hasLoans(@PathVariable Long userId) {
        List<LoanDTO> loans = loanService.getLoansByUserId(userId);
        return ResponseEntity.ok(Map.of(
            "userId", userId,
            "hasLoans", !loans.isEmpty(),
            "loanCount", loans.size(),
            "activeLoans", loans.stream().filter(loan -> "ACTIVE".equals(loan.getStatus())).count(),
            "pendingLoans", loans.stream().filter(loan -> "PENDING".equals(loan.getStatus())).count()
        ));
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get all loans for a user by username")
    public ResponseEntity<List<LoanDTO>> getLoansByUsername(@PathVariable String username) {
        return ResponseEntity.ok(loanService.getLoansByUsername(username));
    }

    @GetMapping("/username/{username}/has-loans")
    @Operation(summary = "Check if a user has any loans by username")
    public ResponseEntity<Map<String, Object>> hasLoansByUsername(@PathVariable String username) {
        List<LoanDTO> loans = loanService.getLoansByUsername(username);
        return ResponseEntity.ok(Map.of(
            "username", username,
            "hasLoans", !loans.isEmpty(),
            "loanCount", loans.size()
        ));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a loan")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.updateLoan(id, loanDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a loan")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    @Operation(summary = "Get total number of loans")
    public ResponseEntity<Long> countLoans() {
        return ResponseEntity.ok(loanService.countLoans());
    }

    @PostMapping("/transfer")
    @Operation(summary = "Transfer loans from one user to another. If loanIds is not provided, transfers all loans from the source user.")
    public ResponseEntity<Void> transferLoans(@Valid @RequestBody LoanTransferDTO transferDTO) {
        loanService.transferLoans(transferDTO);
        return ResponseEntity.ok().build();
    }
} 