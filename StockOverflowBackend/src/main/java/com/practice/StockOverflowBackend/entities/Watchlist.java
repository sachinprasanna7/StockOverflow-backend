package com.practice.StockOverflowBackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "watchlists")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int watchlistId;



    private String name;

    // Default constructor required by JPA
    public Watchlist() {
    }

    public Watchlist(int watchlistId, String name) {
        this.watchlistId = watchlistId;
        this.name = name;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public String getWatchlistName() {
        return name;
    }
    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }
    public void setWatchlistName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Watchlist{" +
                "watchlistId=" + watchlistId +
                ", name='" + name + '\'' +
                '}';
    }
}
