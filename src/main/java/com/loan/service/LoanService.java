package com.loan.service;

import com.loan.dto.LoanDTO;
import java.util.List;

public interface LoanService {
    LoanDTO createLoan(LoanDTO loanDTO);
    LoanDTO getLoanById(Long id);
    List<LoanDTO> getAllLoans();
    List<LoanDTO> getLoansByUserId(Long userId);
    LoanDTO updateLoan(Long id, LoanDTO loanDTO);
    void deleteLoan(Long id);
    long countLoans();
    boolean existsById(Long id);
    void transferLoans(String fromUsername, String toUsername);
} 