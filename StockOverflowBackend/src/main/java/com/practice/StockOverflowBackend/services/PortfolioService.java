package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Portfolio;
import com.practice.StockOverflowBackend.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<Portfolio> getPortfolios() {
        return portfolioRepository.findAll();
    }

    public void buyStock(int symbolId, int quantity, BigDecimal pricePerStock) {
        BigDecimal investment = pricePerStock.multiply(BigDecimal.valueOf(quantity));

        Optional<Portfolio> existingEntry = portfolioRepository.findById(symbolId);

        if (existingEntry.isPresent()) {
            Portfolio p = existingEntry.get();
            p = new Portfolio(
                    p.getSymbolId(),
                    p.getStockQuantity() + quantity,
                    p.getMoneyInvested().add(investment)
            );
            portfolioRepository.save(p);
        } else {
            Portfolio newEntry = new Portfolio(symbolId, quantity, investment);
            portfolioRepository.save(newEntry);
        }
    }

    public void sellStock(int symbolId, int quantity, BigDecimal pricePerStock) throws Exception {
        Optional<Portfolio> existingEntry = portfolioRepository.findById(symbolId);

        if (existingEntry.isPresent()) {
            Portfolio p = existingEntry.get();

            if (p.getStockQuantity() < quantity) {
                throw new Exception("Not enough stock to sell.");
            }

            int remainingQuantity = p.getStockQuantity() - quantity;
            BigDecimal averagePriceOfInvestment = p.getMoneyInvested().divide(BigDecimal.valueOf(p.getStockQuantity()), 4, RoundingMode.HALF_UP);
            BigDecimal reduction = averagePriceOfInvestment.multiply(BigDecimal.valueOf(quantity));
            BigDecimal remainingInvestment = p.getMoneyInvested().subtract(reduction);

            if (remainingQuantity == 0) {
                portfolioRepository.deleteById(symbolId);
            } else {
                Portfolio updated = new Portfolio(symbolId, remainingQuantity, remainingInvestment);
                portfolioRepository.save(updated);
            }

        } else {
            throw new Exception("Stock not found in portfolio.");
        }
    }
}
