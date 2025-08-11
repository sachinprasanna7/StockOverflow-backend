package com.practice.StockOverflowBackend.Controllers;

import com.practice.StockOverflowBackend.entities.UserAccount;
import com.practice.StockOverflowBackend.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/useraccount")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/getAccountInfo")
    public @ResponseBody UserAccount getAccountInfo(@RequestParam int userId) throws Exception {
        return userAccountService.getAccountInfo(userId);
    }

    @GetMapping("/getTradingMoney")
    public @ResponseBody Map<String, Object> getTradingMoney(@RequestParam int userId) throws Exception {
        BigDecimal tradingMoney = userAccountService.getTradingAccountMoney(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("trading_money", tradingMoney != null ? tradingMoney : BigDecimal.ZERO);
        return response;
    }

    @GetMapping("/getStockInvestmentsMoney")
    public @ResponseBody Map<String, Object> getStockInvestmentsMoney(@RequestParam int userId) throws Exception {
        BigDecimal stockInvestmentsMoney = userAccountService.getStockInvestmentsMoney(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("stock_investments_money", stockInvestmentsMoney != null ? stockInvestmentsMoney : BigDecimal.ZERO);
        return response;
    }


    @PostMapping("/updateTradingMoney")
    public String updateTradingMoney(@RequestParam int userId,
                                     @RequestParam BigDecimal amount,
                                     @RequestParam boolean isDeposit) {
        try {
            userAccountService.updateTradingAccountMoney(userId, amount, isDeposit);
            return (isDeposit ? "Deposited ₹" : "Withdrew ₹") + amount + " for user ID: " + userId;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Update account information (except permanent fields)
     */
    @PostMapping("/updateInfo")
    public String updateAccountInfo(@RequestParam int userId,
                                    @RequestParam String fullName,
                                    @RequestParam String email,
                                    @RequestParam String phoneNumber,
                                    @RequestParam String address,
                                    @RequestParam String zipCode,
                                    @RequestParam String state,
                                    @RequestParam String bankAccountNumber,
                                    @RequestParam String ifscCode) {
        try {
            userAccountService.updateAccountInfo(userId, fullName, email, phoneNumber,
                    address, zipCode, state, bankAccountNumber, ifscCode);
            return "Updated account info for user ID: " + userId;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Update stock investments money (when buying or selling stocks)
     */
    @PostMapping("/updateStockInvestments")
    public String updateStockInvestments(@RequestParam int userId,
                                         @RequestParam BigDecimal amount,
                                         @RequestParam boolean isBuying) {
        try {
            userAccountService.updateStockInvestmentsMoney(userId, amount, isBuying);
            return (isBuying ? "Added ₹" : "Reduced ₹") + amount + " in stock investments for user ID: " + userId;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
