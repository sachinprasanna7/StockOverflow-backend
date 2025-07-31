package com.practice.StockOverflowBackend.services;


import com.practice.StockOverflowBackend.entities.Watchlist;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public List<Watchlist> getWatchlists() {
        return  watchlistRepository.findAll();
    }

    public void addWatchlist(Watchlist watchlist) {
        if ( watchlistRepository.existsById(watchlist.getWatchlistId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Watchlist with ID: " +
                            watchlist.getWatchlistId() + "already exists"
            );
        }
        System.out.println(watchlist);
        watchlistRepository.save(watchlist);
    }
}

