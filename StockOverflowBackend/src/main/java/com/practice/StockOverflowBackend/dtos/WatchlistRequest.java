package com.practice.StockOverflowBackend.dtos;


public class WatchlistRequest {
    private String name;

    public WatchlistRequest() {}

    public WatchlistRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
