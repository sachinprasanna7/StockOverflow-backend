package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;
import com.practice.StockOverflowBackend.services.WatchlistStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/watchlistStocks")
public class WatchlistStocksController {
    @Autowired
    public WatchlistStocksService watchlistStocksService;

    @PostMapping(path="/addWatchlistStocks")
    public @ResponseBody void addWatchlistStocks(@RequestBody Watchlist_Stocks watchlistStocks) {
        System.out.println(watchlistStocks);
        watchlistStocksService.addWatchlistStocks(watchlistStocks);
    }

    @GetMapping(path="/getWatchlistStocks")
    public @ResponseBody List<Watchlist_Stocks> getWatchlistStocks(){
        return watchlistStocksService.getWatchlistStocks();

    }
}

