package com.loan.config;

import com.loan.entity.Loan;
import com.loan.entity.User;
import com.loan.repository.LoanRepository;
import com.loan.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer {
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        // Create 10 sample users
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

        // Create 10 loans for each user
        for (User user : users) {
            for (int i = 1; i <= 10; i++) {
                Loan loan = new Loan();
                loan.setLoanNumber("LOAN-" + user.getId() + "-" + i);
                loan.setAmount(new BigDecimal(random.nextInt(100000) + 1000));
                loan.setInterestRate(new BigDecimal(random.nextDouble(10) + 1));
                loan.setTerm(random.nextInt(36) + 12);
                loan.setStartDate(LocalDate.now().minusDays(random.nextInt(365)));
                loan.setStatus(Loan.LoanStatus.values()[random.nextInt(Loan.LoanStatus.values().length)]);
                loan.setUser(user);
                loanRepository.save(loan);
            }
        }
    }
} 