package com.rvz.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvz.transfer.entity.User;

// implement with jpa repository with inbuilt implementation
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByAccountnumber(int accountnumber);
}
