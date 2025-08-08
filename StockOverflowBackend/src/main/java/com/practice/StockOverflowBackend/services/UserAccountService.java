package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.UserAccount;
import com.practice.StockOverflowBackend.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount getAccountInfo(int userId) throws Exception {
        return userAccountRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with ID: " + userId));
    }

    public BigDecimal getTradingAccountMoney(int userId) throws Exception {
        Optional<UserAccount> optionalUser = userAccountRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with ID: " + userId);
        }

        UserAccount user = optionalUser.get();
        BigDecimal tradingMoney = user.getTradingAccountMoney();
        return tradingMoney != null ? tradingMoney : BigDecimal.ZERO;
    }

    public BigDecimal getStockInvestmentsMoney(int userId) throws Exception {
        Optional<UserAccount> optionalUser = userAccountRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with ID: " + userId);
        }

        UserAccount user = optionalUser.get();
        BigDecimal stockInvestmentsMoney = user.getStockInvestmentsMoney();
        return stockInvestmentsMoney != null ? stockInvestmentsMoney : BigDecimal.ZERO;
    }



    public void updateTradingAccountMoney(int userId, BigDecimal amount, boolean isDeposit) throws Exception {
        Optional<UserAccount> optionalUser = userAccountRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with ID: " + userId);
        }

        UserAccount user = optionalUser.get();

        BigDecimal currentBalance = user.getTradingAccountMoney();
        if (currentBalance == null) {
            currentBalance = BigDecimal.ZERO;
        }

        if (isDeposit) {
            user.setTradingAccountMoney(currentBalance.add(amount));
        } else {
            if (currentBalance.compareTo(amount) < 0) {
                throw new Exception("Insufficient funds to withdraw!");
            }
            user.setTradingAccountMoney(currentBalance.subtract(amount));
        }

        userAccountRepository.save(user);
    }

    /**
     * Update account information (except permanent fields like client/demat numbers).
     */
    public void updateAccountInfo(int userId, String fullName, String email, String phoneNumber,
                                  String address, String zipCode, String state,
                                  String bankAccountNumber, String ifscCode) throws Exception {
        Optional<UserAccount> optionalUser = userAccountRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with ID: " + userId);
        }

        UserAccount user = optionalUser.get();

        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setZipCode(zipCode);
        user.setState(state);
        user.setBankAccountNumber(bankAccountNumber);
        user.setIfscCode(ifscCode);

        userAccountRepository.save(user);
    }

    /**
     * Update stock investment money (when buying or selling stocks).
     */
    public void updateStockInvestmentsMoney(int userId, BigDecimal amount, boolean isBuying) throws Exception {
        Optional<UserAccount> optionalUser = userAccountRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found with ID: " + userId);
        }

        UserAccount user = optionalUser.get();

        BigDecimal currentInvestments = user.getStockInvestmentsMoney();
        if (currentInvestments == null) {
            currentInvestments = BigDecimal.ZERO;
        }

        if (isBuying) {
            user.setStockInvestmentsMoney(currentInvestments.add(amount));
        } else {
            if (currentInvestments.compareTo(amount) < 0) {
                throw new Exception("Not enough stock investment value to reduce!");
            }
            user.setStockInvestmentsMoney(currentInvestments.subtract(amount));
        }

        userAccountRepository.save(user);
    }
}