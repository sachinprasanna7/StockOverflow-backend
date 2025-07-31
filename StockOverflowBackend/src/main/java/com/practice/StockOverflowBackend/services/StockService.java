package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.entities.Stocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StocksRepository stocksRepository;

    public List<Stocks> getStocks() {
        return stocksRepository.findAll();
    }

    public void addStocks(Stocks stocks) {
        if (stocksRepository.existsById(stocks.getSymbol_id())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Stock with ID: " +
                            stocks.getSymbol_id() + "already exists"
            );
        }
        stocksRepository.save(stocks);
    }
}
