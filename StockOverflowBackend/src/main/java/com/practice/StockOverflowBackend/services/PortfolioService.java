package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Portfolio;
import com.practice.StockOverflowBackend.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Optional<Portfolio> existing = portfolioRepository.findBySymbolId(symbolId);

        if (existing.isPresent()) {
            Portfolio portfolio = existing.get();
            // Increase quantity
            System.out.println("Old quantity: " + (portfolio.getStockQuantity() ));
            System.out.println("New quantity: " + (portfolio.getStockQuantity() + quantity));
            BigDecimal totalCostOld = portfolio.getAveragePrice().multiply(BigDecimal.valueOf(portfolio.getStockQuantity()));
            BigDecimal totalCostNew = pricePerStock.multiply(BigDecimal.valueOf(quantity));
            BigDecimal avgPrice = (totalCostOld.add(totalCostNew))
                    .divide(BigDecimal.valueOf(portfolio.getStockQuantity() + quantity), BigDecimal.ROUND_HALF_UP);
            System.out.println(totalCostOld);
            System.out.println(totalCostNew);
            System.out.println(avgPrice);
            portfolio.setStockQuantity(portfolio.getStockQuantity() + quantity);


            // Optionally update average buy price


            portfolio.setAveragePrice(avgPrice);
            portfolioRepository.save(portfolio);

        } else {
            Portfolio newPortfolio = new Portfolio();
            newPortfolio.setSymbolId(symbolId);
            newPortfolio.setStockQuantity(quantity);
            newPortfolio.setAveragePrice(pricePerStock);
            portfolioRepository.save(newPortfolio);
        }
    }

    public void sellStock(int symbolId, int quantity, BigDecimal pricePerStock) throws Exception {
        Optional<Portfolio> existing = portfolioRepository.findBySymbolId(symbolId);

        if (existing.isPresent()) {
            Portfolio portfolio = existing.get();

            if (portfolio.getStockQuantity() < quantity) {
                throw new Exception("Not enough quantity to sell!");
            }

            int updatedQuantity = portfolio.getStockQuantity() - quantity;

            if (updatedQuantity == 0) {
                portfolioRepository.delete(portfolio);
            } else {
                portfolio.setStockQuantity(updatedQuantity);
                portfolioRepository.save(portfolio);
            }
        } else {
            throw new Exception("Stock not found in portfolio!");
        }
    }
}
