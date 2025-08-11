package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.entities.Stocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StocksRepository stocksRepository;

    public List<Stocks> getStocks() {

        return stocksRepository.findAll();
    }

    public Stocks getStockById(int stock_id){
        return stocksRepository.findById(stock_id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Stock with ID: "+stock_id+" not found"
                ));
    }

    public List<Stocks> searchStocks(String query){
        String lowerQuery = query.toLowerCase(Locale.ROOT);
        List<Stocks> allStocks = stocksRepository.findAll();

        return allStocks.stream().filter(
                stock->
                        stock.getSymbol().toLowerCase().contains(lowerQuery) ||
                        stock.getCompanyName().toLowerCase().contains(lowerQuery)
                )
                .collect(Collectors.toList());
    }

//    public void addStocks(Stocks stocks) {
//        if (stocksRepository.existsById(stocks.getSymbol_id())){
//            throw new ResponseStatusException(
//                    HttpStatus.CONFLICT,
//                    "Stock with ID: " +
//                            stocks.getSymbol_id() + "already exists"
//            );
//        }
//        System.out.println(stocks);
//        stocksRepository.save(stocks);
//    }
public Stocks getStockBySymbol(String symbol) {
    return stocksRepository.findBySymbolIgnoreCase(symbol)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Stock with symbol: " + symbol + " not found"
            ));
}

}
