package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;
import com.practice.StockOverflowBackend.services.WatchlistStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/watchlistStocks")
public class WatchlistStocksController {
    @Autowired
    public WatchlistStocksService watchlistStocksService;

    @PostMapping(path="/addWatchlistStocks")
    public @ResponseBody void addWatchlistStocks(@RequestBody WatchlistStockCompositeKey watchlistStocks) {
        //System.out.println(watchlistStocks);
        watchlistStocksService.addWatchlistStocks(watchlistStocks);
    }

    @GetMapping(path="/getWatchlistStocks/{id}")
    public @ResponseBody List<Watchlist_Stocks> getWatchlistStocksById(@PathVariable int id) {
        return watchlistStocksService.getWatchlistStocksById(id);
    }


    @DeleteMapping(path="/deleteWatchlistStocksById")
        public @ResponseBody void deleteWatchlistStocksById(@RequestBody WatchlistStockCompositeKey watchlistStocks) {
           watchlistStocksService.deleteWatchlistStocksBySymbolId(watchlistStocks);
        }

}

