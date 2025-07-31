package com.practice.StockOverflowBackend.Interfaces;

import com.practice.StockOverflowBackend.Models.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
    // You can add custom queries if needed
}
