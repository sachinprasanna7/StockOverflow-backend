package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Order_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<Order_History, Integer> {
    // JpaRepository already provides methods like save, findById, findAll, deleteById

    // Method 2: Custom JPQL query for partial search
    @Query("""
    SELECT o FROM Order_History o
    WHERE LOWER(o.stock.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(o.stock.symbol) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Order_History> searchByStockNameOrSymbol(@Param("keyword") String keyword);
}
