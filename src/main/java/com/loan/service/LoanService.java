package com.loan.service;

import com.loan.dto.LoanDTO;
import com.loan.dto.LoanTransferDTO;
import java.util.List;

public interface LoanService {
    LoanDTO createLoan(LoanDTO loanDTO);
    LoanDTO getLoanById(Long id);
    List<LoanDTO> getAllLoans();
    List<LoanDTO> getLoansByUserId(Long userId);
    List<LoanDTO> getLoansByUsername(String username);
    LoanDTO updateLoan(Long id, LoanDTO loanDTO);
    void deleteLoan(Long id);
    Long countLoans();
    boolean existsById(Long id);
    void transferLoans(LoanTransferDTO transferDTO);
    boolean hasLoans(String username);
} 