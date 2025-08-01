package com.practice.StockOverflowBackend.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class WatchlistStockCompositeKey implements Serializable {
    @Column(name="watchlist_id")
    private int watchlistId;
    @Column(name="symbol_id")
    private int symbolId;

    public int getWatchlistId(){
        return  watchlistId;
    }

    public void setWatchlistId(int watchlistId){
        this.watchlistId=watchlistId;
    }

    public int getSymbolId(){
        return  symbolId;
    }

    public void setSymbolId(int symbolId){
        this.symbolId=symbolId;
    }
}
