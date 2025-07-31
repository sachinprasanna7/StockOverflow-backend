package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/stock")
public class StocksController {
        @Autowired
        public StockService stockService;

    @PostMapping(path="/addStocks")
    public @ResponseBody void addStocks (@RequestBody Stocks stocks) {

            stockService.addStocks(stocks);
    }

    @GetMapping(path="/getStocks")
    public @ResponseBody Iterable<Stocks> getAllCompany(){
        return stockService.getStocks();

    }
}
