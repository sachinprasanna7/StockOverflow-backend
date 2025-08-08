package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import com.practice.StockOverflowBackend.dtos.WatchlistWithStocksDTO;
import com.practice.StockOverflowBackend.services.WatchlistStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/watchlistStocks")
public class WatchlistStocksController {

    @Autowired
    private WatchlistStocksService watchlistStocksService;

    // Add a stock to a watchlist
    @PostMapping(path = "/add")
    public ResponseEntity<Void> addWatchlistStocks(@RequestBody WatchlistStockCompositeKey watchlistStocks) {
        watchlistStocksService.addWatchlistStocks(watchlistStocks);
        return ResponseEntity.status(201).build();  // 201 Created, no body
    }

    // Get all stocks in a watchlist by watchlistId
    @GetMapping(path = "/{id}")
    public ResponseEntity<WatchlistWithStocksDTO> getWatchlistStocksById(@PathVariable int id) {
        WatchlistWithStocksDTO dto = watchlistStocksService.getWatchlistStocksById(id);
        return ResponseEntity.ok(dto);  // 200 OK with body
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<WatchlistWithStocksDTO>> getWatchlistStocks() {
        List<WatchlistWithStocksDTO> dto = watchlistStocksService.getWatchlistStocks();
        return ResponseEntity.ok(dto);  // 200 OK with body
    }

    // Delete a stock from a watchlist by composite key
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Void> deleteWatchlistStocksById(@RequestBody WatchlistStockCompositeKey watchlistStocks) {
        watchlistStocksService.deleteWatchlistStocksBySymbolId(watchlistStocks);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
