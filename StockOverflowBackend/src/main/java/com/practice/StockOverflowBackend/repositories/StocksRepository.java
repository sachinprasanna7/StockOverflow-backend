package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer> {

//    @Query("Select symbol, company_name from stocks where lower(company_name) like lower(concat('%', :query, '%')) or lower(symbol) like lower(concat('%', :query, '%'))")
//    List<Stocks> searchByNameOrSymbol(@Param("query") String query);
}
