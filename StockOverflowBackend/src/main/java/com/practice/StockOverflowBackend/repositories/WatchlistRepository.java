package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
    // You can add custom queries if needed
}
