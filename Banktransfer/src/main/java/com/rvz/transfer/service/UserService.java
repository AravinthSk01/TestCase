package com.rvz.transfer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.rvz.transfer.entity.Transaction;
import com.rvz.transfer.entity.User;
import com.rvz.transfer.repository.TransactionRepository;
import com.rvz.transfer.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    TransactionRepository transactionRepository;

    // Add or update user balance
    public String addAmount(User d) {
        userRepository.save(d);
        return "ok";
    }

    // View all users
    public List<User> viewAmount() {
        return userRepository.findAll();
    }

    // View single user profile by account number
    public User viewProfile(int accountNumber) {
        return userRepository.findByAccountnumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String transferAmount(int fromAccount, int toAccount, int amount, String receiverName) {
        User sender = userRepository.findByAccountnumber(fromAccount)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found"));

        if (sender.getAmount() < amount) {
            return "Insufficient balance!";
        }

        // Deduct from sender
        sender.setAmount(sender.getAmount() - amount);
        userRepository.save(sender);

        // Log sender transaction
        Transaction debit = new Transaction();
        debit.setFromAccount(fromAccount);
        debit.setToAccount(toAccount);
        debit.setAmount(amount);
        debit.setTimestamp(LocalDateTime.now());
        debit.setType("DEBIT");
        transactionRepository.save(debit);

        // Find or create receiver
        User receiver = userRepository.findByAccountnumber(toAccount).orElse(null);
        if (receiver == null) {
            receiver = new User();
            receiver.setAccountnumber(toAccount);
            receiver.setUsername(receiverName);
            receiver.setAmount(0);
        }

        receiver.setAmount(receiver.getAmount() + amount);
        userRepository.save(receiver);

        // Log receiver transaction
        Transaction credit = new Transaction();
        credit.setFromAccount(fromAccount);
        credit.setToAccount(toAccount);
        credit.setAmount(amount);
        credit.setTimestamp(LocalDateTime.now());
        credit.setType("CREDIT");
        transactionRepository.save(credit);

        return "Transfer successful!";
    }

    public List<Transaction> getUserTransactions(int accountNumber) {
        List<Transaction> sent = transactionRepository.findByFromAccount(accountNumber);
        List<Transaction> received = transactionRepository.findByToAccount(accountNumber);
        sent.addAll(received);
        return sent;
    }


}
