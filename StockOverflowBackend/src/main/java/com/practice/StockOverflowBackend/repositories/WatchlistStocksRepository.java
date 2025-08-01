package com.practice.StockOverflowBackend.repositories;


import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WatchlistStocksRepository extends JpaRepository<Watchlist_Stocks, WatchlistStockCompositeKey> {
    // You can add custom queries if needed
    List<Watchlist_Stocks> findByCompositeKeyWatchlistId(int watchlistId);
    List<Watchlist_Stocks> findByCompositeKeySymbolId(int symbolId);
    List<Watchlist_Stocks> findByCompositeKeyWatchlistIdAndCompositeKeySymbolId(int watchlistId, int symbolId);
    void deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(int watchlistId, int symbolId);
}
