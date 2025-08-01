package com.practice.StockOverflowBackend.Controllers;

import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/stock")
public class StocksController {
        @Autowired
        public StockService stockService;

    @PostMapping(path="/addStocks")
    public @ResponseBody void addStocks (@RequestBody Stocks stocks) {
            System.out.println(stocks);
            stockService.addStocks(stocks);
    }

    @GetMapping(path="/getStocks")
    public @ResponseBody List<Stocks> getAllCompany(){
        return stockService.getStocks();

    }
}
