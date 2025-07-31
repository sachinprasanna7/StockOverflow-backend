package com.practice.StockOverflowBackend.Models;

public class Watchlist_Stocks {
    private int watchlistId;
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
