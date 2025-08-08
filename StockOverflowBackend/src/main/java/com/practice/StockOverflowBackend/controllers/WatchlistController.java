package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.dtos.WatchlistRequest;
import com.practice.StockOverflowBackend.entities.Watchlist;
import com.practice.StockOverflowBackend.services.WatchlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    // Add a watchlist and return the created entity
    @PostMapping(path = "/addWatchlist")
    public ResponseEntity<Watchlist> addWatchlist(@RequestBody WatchlistRequest request) {
        Watchlist savedWatchlist = watchlistService.addWatchlist(request.getName());
        return ResponseEntity.status(201).body(savedWatchlist); // HTTP 201 Created
    }

    // Get all watchlists
    @GetMapping(path = "/getWatchlists")
    public ResponseEntity<List<Watchlist>> getWatchlists() {
        List<Watchlist> watchlists = watchlistService.getWatchlists();
        return ResponseEntity.ok(watchlists);
    }

    // Delete a watchlist by ID
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteWatchlistById(@PathVariable int id) {
        watchlistService.deleteWatchlistById(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
