package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {


    Optional<Portfolio> findBySymbolId(int symbolId);

}
