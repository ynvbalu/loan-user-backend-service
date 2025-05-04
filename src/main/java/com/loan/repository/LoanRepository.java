package com.loan.repository;

import com.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    boolean existsByLoanNumber(String loanNumber);
    long count();
    List<Loan> findByUserUsernameIgnoreCase(String username);
    boolean existsByUserUsernameIgnoreCase(String username);
} 