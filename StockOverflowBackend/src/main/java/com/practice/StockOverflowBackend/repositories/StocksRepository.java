package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer> {

}
