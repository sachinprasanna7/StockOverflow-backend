package com.practice.StockOverflowBackend;

import org.springframework.data.repository.CrudRepository;

public interface StocksRepository extends CrudRepository<Stocks, Integer> {

}
