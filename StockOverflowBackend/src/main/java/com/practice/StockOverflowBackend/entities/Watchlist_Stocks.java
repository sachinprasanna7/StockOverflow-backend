package com.practice.StockOverflowBackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "watchlist_stocks")
public class Watchlist_Stocks {
    public Watchlist_Stocks(){}
    @Id
    @Column(name="watchlist_id")
    private int watchlistId;
    @Column(name="symbol_id")
    private int symbolId;

    Watchlist_Stocks(int watchlistId, int symbolId) {
        this.watchlistId = watchlistId;
        this.symbolId = symbolId;
    }

    public int getWatchlistId() { return watchlistId; }

    public int getSymbolId() { return symbolId; }

    @Override
    public String toString() {
        return "WatchlistStock{" +
                "watchlistId=" + watchlistId +
                ", symbolId=" + symbolId +
                '}';
    }
}
