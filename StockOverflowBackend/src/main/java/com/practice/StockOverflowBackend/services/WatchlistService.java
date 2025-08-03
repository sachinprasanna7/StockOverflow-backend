package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Watchlist;
import com.practice.StockOverflowBackend.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    // Get all watchlists
    public ResponseEntity<List<Watchlist>> getWatchlists() {
        List<Watchlist> watchlists = watchlistRepository.findAll();
        return ResponseEntity.ok(watchlists);
    }

    // Add a new watchlist
    public ResponseEntity<Watchlist> addWatchlist(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Watchlist name is required");
        }

        try {
            Watchlist watchlist = new Watchlist();
            watchlist.setWatchlistName(name);
            // ID will be auto-generated
            Watchlist saved = watchlistRepository.save(watchlist);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving watchlist");
        }
    }

    public void deleteWatchlistById(int watchlistId) {
        // Check if the watchlist exists
        boolean exists = watchlistRepository.existsById(watchlistId);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Watchlist not found with ID: " + watchlistId);
        }

        try {
            watchlistRepository.deleteById(watchlistId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete watchlist with ID: " + watchlistId);
        }
    }

}
