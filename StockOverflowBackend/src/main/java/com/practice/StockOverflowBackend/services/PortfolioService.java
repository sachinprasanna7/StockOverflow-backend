package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Portfolio;
import com.practice.StockOverflowBackend.repositories.PortfolioRepository;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.entities.Stocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<Portfolio> getPortfolios() {
        return portfolioRepository.findAll();
    }

    public void addPortfolio(Portfolio portfolio) {
        if (portfolioRepository.existsById(portfolio.getSymbolId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Portfolio with ID: " +
                            portfolio.getSymbolId() + "already exists"
            );
        }
        System.out.println(portfolio);
        portfolioRepository.save(portfolio);
    }
}
