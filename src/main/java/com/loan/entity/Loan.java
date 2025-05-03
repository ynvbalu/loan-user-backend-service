package com.loan.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String loanNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal interestRate;

    @Column(nullable = false)
    private Integer term;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        if (this.loanNumber == null) {
            this.loanNumber = generateLoanNumber();
        }
        if (this.startDate == null) {
            this.startDate = LocalDateTime.now();
        }
    }

    private String generateLoanNumber() {
        return "LOAN-" + System.currentTimeMillis();
    }

    public enum LoanStatus {
        ACTIVE, CLOSED, PENDING
    }
} 