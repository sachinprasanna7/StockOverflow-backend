package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    Optional<UserAccount> findByEmail(String email);

    Optional<UserAccount> findById(int id);

}