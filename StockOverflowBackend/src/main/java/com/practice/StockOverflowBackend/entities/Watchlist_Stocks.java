package com.practice.StockOverflowBackend.entities;

import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import jakarta.persistence.*;

@Entity
@Table(name = "watchlist_stocks")
public class Watchlist_Stocks {
    public Watchlist_Stocks(){}
    @EmbeddedId
    private WatchlistStockCompositeKey compositeKey;

        public void setCompositeKey( WatchlistStockCompositeKey compositeKey) {
        this.compositeKey= compositeKey;
    }

    public int getWatchlistId() { return compositeKey.getWatchlistId(); }

    public int getSymbolId() { return compositeKey.getSymbolId(); }

    @Override
    public String toString() {
        return "WatchlistStock{" +
                "watchlistId=" + compositeKey.getWatchlistId() +
                ", symbolId=" + compositeKey.getSymbolId() +
                '}';
    }
}
