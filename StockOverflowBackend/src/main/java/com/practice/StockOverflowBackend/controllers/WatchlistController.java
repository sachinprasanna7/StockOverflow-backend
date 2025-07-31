package com.practice.StockOverflowBackend.controllers;


import com.practice.StockOverflowBackend.entities.Watchlist;

import com.practice.StockOverflowBackend.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/watchlist")
public class WatchlistController {
    @Autowired
    public WatchlistService watchlistService;

    @PostMapping(path="/addWatchlistStocks")
    public @ResponseBody void addWatchlists (@RequestBody Watchlist watchlist) {
        System.out.println(watchlist);
        watchlistService.addWatchlist(watchlist);
    }

    @GetMapping(path="/getWatchlistStocks")
    public @ResponseBody List<Watchlist> getWatchlists(){
        return watchlistService.getWatchlists();

    }
}

