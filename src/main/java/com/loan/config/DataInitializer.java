package com.loan.config;

import com.loan.entity.Loan;
import com.loan.entity.User;
import com.loan.repository.LoanRepository;
import com.loan.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    private final Random random = new Random();

    @PostConstruct
    public void init() {
        // Create sample users
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            user.setEmail("user" + i + "@example.com");
            user.setPhoneNumber("123-456-789" + i);
            user.setOooStatus(i % 3 == 0); // Every third user is OOO
            users.add(user);
        }
        userRepository.saveAll(users);

        // Create sample loans
        List<Loan> loans = new ArrayList<>();
        for (User user : users) {
            for (int i = 1; i <= 10; i++) {
                Loan loan = new Loan();
                loan.setLoanNumber("LOAN-" + user.getId() + "-" + i);
                loan.setAmount(new BigDecimal(random.nextDouble() * 100000).setScale(2, RoundingMode.HALF_UP));
                loan.setInterestRate(new BigDecimal(random.nextDouble() * 10).setScale(2, RoundingMode.HALF_UP));
                loan.setTerm(random.nextInt(36) + 12); // 12-48 months
                loan.setStartDate(LocalDateTime.now().minusMonths(random.nextInt(24)));
                loan.setStatus(Loan.LoanStatus.values()[random.nextInt(Loan.LoanStatus.values().length)]);
                loan.setUser(user);
                loans.add(loan);
            }
        }
        loanRepository.saveAll(loans);
    }
} 