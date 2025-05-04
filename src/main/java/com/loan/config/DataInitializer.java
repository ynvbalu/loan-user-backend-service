package com.loan.config;

import com.loan.entity.Loan;
import com.loan.entity.User;
import com.loan.repository.LoanRepository;
import com.loan.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    private int loanCounter = 1;

    @PostConstruct
    public void init() {
        // Create sample users
        List<User> users = new ArrayList<>();
        
        User johnSmith = createUser("R566101", "John", "Smith", "john.smith@example.com", "+1-555-0123", false);
        User sarahJohnson = createUser("X234567", "Sarah", "Johnson", "sarah.j@example.com", "+1-555-0124", true);
        User michaelChen = createUser("Y789012", "Michael", "Chen", "michael.chen@example.com", "+1-555-0125", false);
        User emilyRodriguez = createUser("Z345678", "Emily", "Rodriguez", "emily.r@example.com", "+1-555-0126", false);
        User davidKim = createUser("W901234", "David", "Kim", "david.kim@example.com", "+1-555-0127", true);
        
        users.add(johnSmith);
        users.add(sarahJohnson);
        users.add(michaelChen);
        users.add(emilyRodriguez);
        users.add(davidKim);
        
        userRepository.saveAll(users);

        // Create sample loans
        List<Loan> loans = new ArrayList<>();
        
        // Create three loans for John Smith
        loans.add(createLoan(johnSmith, "25000.00", "4.5", 36, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(johnSmith, "15000.00", "5.2", 24, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(johnSmith, "35000.00", "4.8", 48, Loan.LoanStatus.PENDING));
        
        // Create three loans for Sarah Johnson
        loans.add(createLoan(sarahJohnson, "35000.00", "3.8", 48, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(sarahJohnson, "20000.00", "4.2", 36, Loan.LoanStatus.PENDING));
        loans.add(createLoan(sarahJohnson, "45000.00", "4.0", 60, Loan.LoanStatus.ACTIVE));
        
        // Create three loans for Michael Chen
        loans.add(createLoan(michaelChen, "45000.00", "4.0", 60, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(michaelChen, "28000.00", "4.5", 36, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(michaelChen, "32000.00", "4.2", 48, Loan.LoanStatus.PENDING));
        
        // Create three loans for Emily Rodriguez
        loans.add(createLoan(emilyRodriguez, "30000.00", "4.8", 36, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(emilyRodriguez, "22000.00", "4.3", 24, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(emilyRodriguez, "40000.00", "3.9", 48, Loan.LoanStatus.PENDING));
        
        // Create three loans for David Kim
        loans.add(createLoan(davidKim, "50000.00", "3.5", 48, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(davidKim, "38000.00", "4.1", 36, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(davidKim, "42000.00", "3.8", 60, Loan.LoanStatus.PENDING));
        
        loanRepository.saveAll(loans);
    }
    
    private User createUser(String username, String firstName, String lastName, String email, String phoneNumber, boolean oooStatus) {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setOooStatus(oooStatus);
        user.setCreationDate(LocalDateTime.now());
        return user;
    }
    
    private Loan createLoan(User user, String amount, String interestRate, Integer term, Loan.LoanStatus status) {
        Loan loan = new Loan();
        loan.setLoanNumber("Loan-" + loanCounter++);
        loan.setAmount(new BigDecimal(amount));
        loan.setInterestRate(new BigDecimal(interestRate));
        loan.setTerm(term);
        loan.setStatus(status);
        loan.setStartDate(LocalDateTime.now());
        loan.setUser(user);
        return loan;
    }
} 