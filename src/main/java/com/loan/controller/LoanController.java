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