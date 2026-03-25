package com.rvz.transfer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rvz.transfer.entity.Transaction;
import com.rvz.transfer.entity.User;
import com.rvz.transfer.service.UserService;

@RestController
@RequestMapping("/api/deposit")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public String addAmount(@RequestBody User d) {
        return userService.addAmount(d);
    }

    @GetMapping
    public List<User> viewAmount() {
        return userService.viewAmount();
    }

    // Get profile by account number
    @GetMapping("/profile/{accountNumber}")
    public User viewProfile(@PathVariable int accountNumber) {
        return userService.viewProfile(accountNumber);
    }

    @PostMapping("/transfer")
    public String transferAmount(@RequestParam int fromAccount,
                                 @RequestParam int toAccount,
                                 @RequestParam int amount,
                                 @RequestParam(required = false) String receiverName) {
        return userService.transferAmount(fromAccount, toAccount, amount, receiverName);
    }

    

    @GetMapping("/transactions/{accountNumber}")
    public List<Transaction> getTransactions(@PathVariable int accountNumber) {
        return userService.getUserTransactions(accountNumber);
    }
}
