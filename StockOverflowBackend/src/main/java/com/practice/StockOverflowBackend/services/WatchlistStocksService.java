package com.practice.StockOverflowBackend.services;


import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;

import com.practice.StockOverflowBackend.repositories.WatchlistStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.util.List;

@Service
public class WatchlistStocksService {

    @Autowired
    private WatchlistStocksRepository watchlistStocksRepository;

    public List<Watchlist_Stocks> getWatchlistStocks() {
        return  watchlistStocksRepository.findAll();
    }

    public void addWatchlistStocks(Watchlist_Stocks watchlistStocks) {
        if ( watchlistStocksRepository.existsById(watchlistStocks.getWatchlistId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Watchlist with ID: " +
                            watchlistStocks.getWatchlistId() + "already exists"
            );
        }
        System.out.println(watchlistStocks);
        watchlistStocksRepository.save(watchlistStocks);
    }
}

