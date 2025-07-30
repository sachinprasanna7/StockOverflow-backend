package com.practice.StockOverflowBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Controller
@RequestMapping(path="/stock")
public class StocksController {

    @Autowired
    private StocksRepository stocksRepository;

    @PostMapping(path="/addStocks")
    public @ResponseBody String addStocks (@RequestBody Stocks stocks){
        if (stocksRepository.existsById(stocks.getSymbol_id())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Stock with ID: " +
                    stocks.getSymbol_id() + "already exists"
            );
        }
        stocksRepository.save(stocks);
        return "Details got saved";
    }

    @GetMapping(path="/getstocks")
    public @ResponseBody Iterable<Stocks> getAllCompany(){
        return stocksRepository.findAll();
    }
}
