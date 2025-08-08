package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Portfolio;
import com.practice.StockOverflowBackend.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    // Get all holdings
    @GetMapping("/all")
    public List<Portfolio> getAllHoldings() {
        return portfolioService.getPortfolios();
    }

    // Buy stock - if already exists, quantity will be increased
    @PostMapping("/buy")
    public String buyStock(@RequestParam int symbolId,
                           @RequestParam int quantity,
                           @RequestParam BigDecimal pricePerStock) {
        portfolioService.buyStock(symbolId, quantity, pricePerStock);
        return "Bought " + quantity + " units of stock with ID: " + symbolId;
    }

    // Sell stock - quantity reduced, delete if becomes 0
    @PostMapping("/sell")
    public String sellStock(@RequestParam int symbolId,
                            @RequestParam int quantity,
                            @RequestParam BigDecimal pricePerStock) {
        try {
            portfolioService.sellStock(symbolId, quantity, pricePerStock);
            return "Sold " + quantity + " units of stock with ID: " + symbolId;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
