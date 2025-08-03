package com.practice.StockOverflowBackend.dtos;

import com.practice.StockOverflowBackend.dtos.StockDTO;

import java.util.List;

public class WatchlistWithStocksDTO {
    private int watchlistId;
    private String watchlistName;
    private List<StockDTO> stocks;

    public WatchlistWithStocksDTO() {}

    public WatchlistWithStocksDTO(int watchlistId, String watchlistName, List<StockDTO> stocks) {
        this.watchlistId = watchlistId;
        this.watchlistName = watchlistName;
        this.stocks = stocks;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getWatchlistName() {
        return watchlistName;
    }

    public void setWatchlistName(String watchlistName) {
        this.watchlistName = watchlistName;
    }

    public List<StockDTO> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockDTO> stocks) {
        this.stocks = stocks;
    }
}
