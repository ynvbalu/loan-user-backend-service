package com.loan.service.impl;

import com.loan.dto.LoanDTO;
import com.loan.entity.Loan;
import com.loan.entity.User;
import com.loan.repository.LoanRepository;
import com.loan.repository.UserRepository;
import com.loan.service.LoanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    @Override
    public LoanDTO createLoan(LoanDTO loanDTO) {
        User user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + loanDTO.getUserId()));
        
        Loan loan = new Loan();
        updateLoanFromDTO(loan, loanDTO);
        loan.setUser(user);
        return convertToDTO(loanRepository.save(loan));
    }

    @Override
    @Transactional(readOnly = true)
    public LoanDTO getLoanById(Long id) {
        return loanRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
        
        User user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + loanDTO.getUserId()));
        
        updateLoanFromDTO(loan, loanDTO);
        loan.setUser(user);
        return convertToDTO(loanRepository.save(loan));
    }

    @Override
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new EntityNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countLoans() {
        return loanRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return loanRepository.existsById(id);
    }

    @Override
    public void transferLoans(String fromUsername, String toUsername) {
        User fromUser = userRepository.findByUsername(fromUsername)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + fromUsername));
        
        User toUser = userRepository.findByUsername(toUsername)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + toUsername));

        List<Loan> loans = loanRepository.findByUserId(fromUser.getId());
        for (Loan loan : loans) {
            loan.setUser(toUser);
        }
        loanRepository.saveAll(loans);
    }

    private LoanDTO convertToDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setLoanNumber(loan.getLoanNumber());
        dto.setAmount(loan.getAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setTerm(loan.getTerm());
        dto.setStartDate(loan.getStartDate());
        dto.setStatus(loan.getStatus());
        dto.setUserId(loan.getUser().getId());
        return dto;
    }

    private void updateLoanFromDTO(Loan loan, LoanDTO dto) {
        loan.setLoanNumber(dto.getLoanNumber());
        loan.setAmount(dto.getAmount());
        loan.setInterestRate(dto.getInterestRate());
        loan.setTerm(dto.getTerm());
        loan.setStartDate(dto.getStartDate());
        loan.setStatus(dto.getStatus());
    }
} 