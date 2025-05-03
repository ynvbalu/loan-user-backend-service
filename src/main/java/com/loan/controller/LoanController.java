package com.loan.controller;

import com.loan.dto.LoanDTO;
import com.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Tag(name = "Loan Management", description = "APIs for managing loans")
public class LoanController {
    private final LoanService loanService;

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
    @Operation(summary = "Get loan by ID")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all loans for a user")
    public ResponseEntity<List<LoanDTO>> getLoansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUserId(userId));
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
    @Operation(summary = "Get total count of loans")
    public ResponseEntity<Long> countLoans() {
        return ResponseEntity.ok(loanService.countLoans());
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if loan exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.existsById(id));
    }

    @PostMapping("/transfer")
    @Operation(summary = "Transfer loans between users")
    public ResponseEntity<Void> transferLoans(
            @RequestParam String fromUsername,
            @RequestParam String toUsername) {
        loanService.transferLoans(fromUsername, toUsername);
        return ResponseEntity.ok().build();
    }
} 