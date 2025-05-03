package com.loan.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private boolean oooStatus;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
} 