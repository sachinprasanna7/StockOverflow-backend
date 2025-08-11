package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/stock")
public class StocksController {
        @Autowired
        public StockService stockService;

//    @PostMapping(path="/addStocks")
//    public @ResponseBody void addStocks (@RequestBody Stocks stocks) {
//            System.out.println(stocks);
//            stockService.addStocks(stocks);
//    }

    @GetMapping(path="/getStocks")
    public @ResponseBody List<Stocks> getAllCompany(){
        return stockService.getStocks();

    }

    @GetMapping(path="/{stock_id}")
    public @ResponseBody Stocks getStockForId(@PathVariable int stock_id){
        return stockService.getStockById(stock_id);
    }


    //use /search?query=value
    @GetMapping(path="/search")
    public @ResponseBody List<Stocks> searchStock(@RequestParam("query") String query){
        return stockService.searchStocks(query);
    }
    @GetMapping("/symbol/{symbol}")
    public Stocks getStockBySymbol(@PathVariable String symbol) {
        return stockService.getStockBySymbol(symbol);
    }
}
