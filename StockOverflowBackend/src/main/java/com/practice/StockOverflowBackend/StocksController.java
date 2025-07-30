package com.practice.StockOverflowBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path="/stock")
public class StocksController {

    @Autowired
    private StocksRepository stocksRepository;

    @PostMapping(path="/addStocks")
    public @ResponseBody String addStocks (@RequestParam int symbol_id, @RequestParam String symbol, @RequestParam String company_name){
//        Stocks stocks = new Stocks();
//        stocks.setSymbol_id(symbol_id);
//        stocks.setSymbol(symbol);
//        stocks.setCompany_name(company_name);
//        stocksRepository.save(stocks);
        System.out.println(company_name);
        return "Details got saved";
    }

    @GetMapping(path="/getstocks")
    public @ResponseBody Iterable<Stocks> getAllCompany(){
        return stocksRepository.findAll();
    }
}
