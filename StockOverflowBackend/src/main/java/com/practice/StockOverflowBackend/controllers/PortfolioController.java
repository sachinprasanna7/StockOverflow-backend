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

    @GetMapping("/all")
    public List<Portfolio> getAllHoldings() {
        return portfolioService.getPortfolios();
    }

    @PostMapping("/buy")
    public void buyStock(@RequestParam int symbolId,
                         @RequestParam int quantity,
                         @RequestParam BigDecimal pricePerStock) {
        portfolioService.buyStock(symbolId, quantity, pricePerStock);
    }

    @PostMapping("/sell")
    public void sellStock(@RequestParam int symbolId,
                          @RequestParam int quantity,
                          @RequestParam BigDecimal pricePerStock) throws Exception {
        portfolioService.sellStock(symbolId, quantity, pricePerStock);
    }
}
