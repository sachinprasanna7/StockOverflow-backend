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

    // Get all stocks in a watchlist by watchlistId
    public WatchlistWithStocksDTO getWatchlistStocksById(int watchlistId) {
        try {
            Watchlist watchlist = watchlistRepository.findById(watchlistId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found with ID: " + watchlistId));

            List<Watchlist_Stocks> watchlistStocks = watchlistStocksRepository.findByCompositeKeyWatchlistId(watchlistId);

            List<StockDTO> stocks = watchlistStocks.stream()
                    .map(ws -> new StockDTO(
                            ws.getStock().getSymbol_id(),
                            ws.getStock().getSymbol(),
                            ws.getStock().getCompanyName()
                    ))
                    .toList();

            return new WatchlistWithStocksDTO(
                    watchlist.getWatchlistId(),
                    watchlist.getWatchlistName(),
                    stocks
            );
        } catch (Exception e) {
            // Log exception if you have a logger, or rethrow with internal server error
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get stocks for watchlist ID: " + watchlistId);
        }
    }

    public void addWatchlistStocks(WatchlistStockCompositeKey compositeKey) {
        try {
            // Check if stock exists
            Stocks stock = stocksRepository.findById(compositeKey.getSymbolId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock does not exist with Symbol ID: " + compositeKey.getSymbolId()));

            // Check if watchlist exists
            Watchlist watchlist = watchlistRepository.findById(compositeKey.getWatchlistId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Watchlist does not exist with ID: " + compositeKey.getWatchlistId()));

            // Check if this stock is already in the watchlist to prevent duplicates
            boolean exists = watchlistStocksRepository.existsByCompositeKey(compositeKey);
            if (exists) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Stock already exists in the watchlist");
            }

            Watchlist_Stocks watchlistStocks = new Watchlist_Stocks();
            watchlistStocks.setCompositeKey(compositeKey);
            watchlistStocks.setStock(stock);
            watchlistStocks.setWatchlist(watchlist);

            watchlistStocksRepository.save(watchlistStocks);

        } catch (ResponseStatusException e) {
            throw e;  // Let Spring handle these known exceptions
        } catch (Exception e) {
            // Catch other unexpected exceptions
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add stock to watchlist");
        }
    }

    @Transactional
    public void deleteWatchlistStocksBySymbolId(WatchlistStockCompositeKey compositeKey) {
        try {
            // Check if the entry exists before deleting
            boolean exists = watchlistStocksRepository.existsByCompositeKey(compositeKey);
            if (!exists) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock in watchlist not found for deletion");
            }

            watchlistStocksRepository.deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(
                    compositeKey.getWatchlistId(), compositeKey.getSymbolId());

        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete stock from watchlist");
        }
    }

}
