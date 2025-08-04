package com.practice.StockOverflowBackend.services;


import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;

import com.practice.StockOverflowBackend.repositories.WatchlistStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WatchlistStocksService {

    @Autowired
    private WatchlistStocksRepository watchlistStocksRepository;

    //get all watchlist stocks in the same watchlist with watchlistId
    public List<Watchlist_Stocks> getWatchlistStocksById(int watchlistId) {
        List<Watchlist_Stocks> watchlistStocks = watchlistStocksRepository.findByCompositeKeyWatchlistId(watchlistId);
        if (watchlistStocks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No stocks found for the given watchlist ID");
        }
        return watchlistStocks;
    }

    public void addWatchlistStocks(WatchlistStockCompositeKey watchlistStocksCompositeKey) {
        Watchlist_Stocks watchlistStocks = new Watchlist_Stocks();
        watchlistStocks.setCompositeKey(watchlistStocksCompositeKey);
        watchlistStocksRepository.save(watchlistStocks);
    }
    @Transactional
    public void deleteWatchlistStocksBySymbolId(WatchlistStockCompositeKey watchlistStockCompositeKey){

        watchlistStocksRepository.deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(watchlistStockCompositeKey.getWatchlistId(), watchlistStockCompositeKey.getSymbolId());

    }

}

