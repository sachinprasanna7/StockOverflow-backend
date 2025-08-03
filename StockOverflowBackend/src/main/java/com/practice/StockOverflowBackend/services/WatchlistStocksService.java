package com.practice.StockOverflowBackend.services;


import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import com.practice.StockOverflowBackend.dtos.StockDTO;

import com.practice.StockOverflowBackend.dtos.WatchlistWithStocksDTO;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.entities.Watchlist;
import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;

import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.repositories.WatchlistRepository;
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
    @Autowired
    private StocksRepository stocksRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;
    //get all watchlist stocks in the same watchlist with watchlistId
    public WatchlistWithStocksDTO getWatchlistStocksById(int watchlistId) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found"));

        List<Watchlist_Stocks> watchlistStocks = watchlistStocksRepository.findByCompositeKeyWatchlistId(watchlistId);

        List<StockDTO> stocks = watchlistStocks.stream()
                .map(ws -> new StockDTO(
                        ws.getStock().getSymbol_id(),
                        ws.getStock().getSymbol(),
                        ws.getStock().getCompanyName() // assuming this is company name
                ))
                .toList();

        return new WatchlistWithStocksDTO(
                watchlist.getWatchlistId(),
                watchlist.getWatchlistName(),
                stocks
        );

    }

    public void addWatchlistStocks(WatchlistStockCompositeKey watchlistStocksCompositeKey) {
        // Check if stock exists
        Stocks stock = stocksRepository.findById(watchlistStocksCompositeKey.getSymbolId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock does not exist"));

        // Check if watchlist exists
        Watchlist watchlist = watchlistRepository.findById(watchlistStocksCompositeKey.getWatchlistId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Watchlist does not exist"));

        // Create and populate the entity
        Watchlist_Stocks watchlistStocks = new Watchlist_Stocks();
        watchlistStocks.setCompositeKey(watchlistStocksCompositeKey);
        watchlistStocks.setStock(stock);
        watchlistStocks.setWatchlist(watchlist);

        watchlistStocksRepository.save(watchlistStocks);
    }
    @Transactional
    public void deleteWatchlistStocksBySymbolId(WatchlistStockCompositeKey watchlistStockCompositeKey){

        watchlistStocksRepository.deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(watchlistStockCompositeKey.getWatchlistId(), watchlistStockCompositeKey.getSymbolId());

    }



}

