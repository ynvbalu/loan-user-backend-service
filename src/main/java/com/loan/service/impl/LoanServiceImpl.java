package com.loan.service.impl;

import com.loan.dto.LoanDTO;
import com.loan.dto.LoanTransferDTO;
import com.loan.entity.Loan;
import com.loan.entity.User;
import com.loan.repository.LoanRepository;
import com.loan.repository.UserRepository;
import com.loan.service.LoanService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public LoanDTO createLoan(LoanDTO loanDTO) {
        User user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + loanDTO.getUserId()));
        
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setTerm(loanDTO.getTerm());
        loan.setStatus(loanDTO.getStatus());
        loan.setUser(user);
        
        Loan savedLoan = loanRepository.save(loan);
        return convertToDTO(savedLoan);
    }

    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO getLoanById(Long id) {
        return loanRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
    }

    @Override
    public List<LoanDTO> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> getLoansByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
        return loanRepository.findByUserId(user.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LoanDTO updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
        
        loan.setAmount(loanDTO.getAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setTerm(loanDTO.getTerm());
        loan.setStatus(loanDTO.getStatus());
        
        Loan updatedLoan = loanRepository.save(loan);
        return convertToDTO(updatedLoan);
    }

    @Override
    @Transactional
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new EntityNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }

    @Override
    public Long countLoans() {
        return loanRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return loanRepository.existsById(id);
    }

    @Override
    @Transactional
    public void transferLoans(LoanTransferDTO transferDTO) {
        User fromUser = userRepository.findByUsername(transferDTO.getFromUsername())
                .orElseThrow(() -> new EntityNotFoundException("Source user not found with username: " + transferDTO.getFromUsername()));
        
        User toUser = userRepository.findByUsername(transferDTO.getToUsername())
                .orElseThrow(() -> new EntityNotFoundException("Target user not found with username: " + transferDTO.getToUsername()));
        
        List<Loan> loansToTransfer;
        if (transferDTO.getLoanIds() != null && !transferDTO.getLoanIds().isEmpty()) {
            // Transfer specific loans
            loansToTransfer = loanRepository.findAllById(transferDTO.getLoanIds());
            
            // Verify all loans belong to the source user
            loansToTransfer.forEach(loan -> {
                if (!loan.getUser().getId().equals(fromUser.getId())) {
                    throw new IllegalStateException("Loan with ID " + loan.getId() + " does not belong to user: " + transferDTO.getFromUsername());
                }
            });
        } else {
            // Transfer all loans
            loansToTransfer = loanRepository.findByUserId(fromUser.getId());
            if (loansToTransfer.isEmpty()) {
                throw new IllegalStateException("No loans found for user: " + transferDTO.getFromUsername());
            }
        }
        
        loansToTransfer.forEach(loan -> loan.setUser(toUser));
        loanRepository.saveAll(loansToTransfer);
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
        dto.setUsername(loan.getUser().getUsername());
        dto.setEmail(loan.getUser().getEmail());
        return dto;
    }
} 