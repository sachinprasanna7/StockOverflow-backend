package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.repositories.OrderHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository repository;

    public OrderHistoryService(OrderHistoryRepository repository) {
        this.repository = repository;
    }

    // Get all orders
    public List<Order_History> getAllOrders() {
        return repository.findAll();
    }

    // Get order by ID
    public Order_History getOrderById(int id) {
        Optional<Order_History> order = repository.findById(id);
        return order.orElse(null);
    }

    // Create or update an order
    public Order_History saveOrder(Order_History orderHistory) {
        return repository.save(orderHistory);
    }

    // Delete an order by ID
    public void deleteOrder(int id) {
        repository.deleteById(id);
    }
}
