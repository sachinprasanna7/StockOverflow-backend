package com.practice.StockOverflowBackend.controllers;


import com.practice.StockOverflowBackend.dtos.WatchlistRequest;
import com.practice.StockOverflowBackend.entities.Watchlist;

import com.practice.StockOverflowBackend.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/watchlist")
public class WatchlistController {
    @Autowired
    public WatchlistService watchlistService;

    @PostMapping(path="/addWatchlist")
    public @ResponseBody void addWatchlists (@RequestBody WatchlistRequest request) {
        System.out.println(request.getName());
        watchlistService.addWatchlist(request.getName());
    }

    @GetMapping(path="/getWatchlists")
    public @ResponseBody ResponseEntity<List<Watchlist>> getWatchlists(){
        return watchlistService.getWatchlists();

    }


    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteWatchlistById(@PathVariable int id) {
        watchlistService.deleteWatchlistById(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

}

