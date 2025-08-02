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

    @MapsId("symbolId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Stocks stock;

    @MapsId("watchlistId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Watchlist watchlist;
    public int getWatchlistId() { return compositeKey.getWatchlistId(); }

    public int getSymbolId() { return compositeKey.getSymbolId(); }
    public void setStock(Stocks stock) {
        this.stock = stock;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }
    @Override
    public String toString() {
        return "WatchlistStock{" +
                "watchlistId=" + compositeKey.getWatchlistId() +
                ", symbolId=" + compositeKey.getSymbolId() +
                '}';
    }
}
