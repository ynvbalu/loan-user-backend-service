package com.loan.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum LoanStatus {
        ACTIVE, CLOSED, PENDING
    }
} 