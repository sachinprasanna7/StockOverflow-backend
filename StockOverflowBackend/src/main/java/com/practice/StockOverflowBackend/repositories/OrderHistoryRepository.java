package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Order_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<Order_History, Integer> {
    // JpaRepository already provides methods like save, findById, findAll, deleteById
}
