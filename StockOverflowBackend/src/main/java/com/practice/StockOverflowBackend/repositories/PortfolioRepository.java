package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    // JpaRepository provides basic CRUD operations, no need to define methods here
    // Additional custom methods can be defined if needed
}
