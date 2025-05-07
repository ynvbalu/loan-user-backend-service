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
        
        User johnSmith = createUser("ALEX", "Alex", "Turner", "alex.turner@example.com", "+1-555-0128", true);
        User sarahJohnson = createUser("LINDA", "Linda", "Nguyen", "linda.nguyen@example.com", "+1-555-0129", false);
        User michaelChen = createUser("BRIAN", "Brian", "Lopez", "brian.lopez@example.com", "+1-555-0130", false);
        User emilyRodriguez = createUser("SOPHIA", "Sophia", "Patel", "sophia.patel@example.com", "+1-555-0131", true);
        

        
        users.add(johnSmith);
        users.add(sarahJohnson);
        users.add(michaelChen);
        users.add(emilyRodriguez);
        
        
        userRepository.saveAll(users);

        // Create sample loans
        List<Loan> loans = new ArrayList<>();
        
        // Create three loans for John Smith
        loans.add(createLoan(johnSmith, "25000.00", "4.5", 36, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(johnSmith, "15000.00", "5.2", 24, Loan.LoanStatus.ACTIVE));
        
        
        // Create three loans for Sarah Johnson
        loans.add(createLoan(sarahJohnson, "35000.00", "3.8", 48, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(sarahJohnson, "20000.00", "4.2", 36, Loan.LoanStatus.PENDING));
        
        
        // Create three loans for Michael Chen
        loans.add(createLoan(michaelChen, "45000.00", "4.0", 60, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(michaelChen, "28000.00", "4.5", 36, Loan.LoanStatus.ACTIVE));
        
        
        // Create three loans for Emily Rodriguez
        loans.add(createLoan(emilyRodriguez, "30000.00", "4.8", 36, Loan.LoanStatus.ACTIVE));
        loans.add(createLoan(emilyRodriguez, "22000.00", "4.3", 24, Loan.LoanStatus.ACTIVE));
                
        
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