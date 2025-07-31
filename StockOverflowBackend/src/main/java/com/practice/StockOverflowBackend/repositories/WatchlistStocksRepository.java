package com.practice.StockOverflowBackend.repositories;


import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WatchlistStocksRepository extends JpaRepository<Watchlist_Stocks, Integer> {
    // You can add custom queries if needed
}
