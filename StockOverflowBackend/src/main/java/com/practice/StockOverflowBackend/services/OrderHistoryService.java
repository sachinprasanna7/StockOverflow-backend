package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.repositories.OrderHistoryRepository;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;
    private final StocksRepository stockRepository;

    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository,
                               StocksRepository stockRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.stockRepository = stockRepository;
    }

    // Get all orders
    public List<Order_History> getAllOrders() {
        return orderHistoryRepository.findAll();
    }

    // Get order by ID
    public Order_History getOrderById(int id) {
        Optional<Order_History> order = orderHistoryRepository.findById(id);
        return order.orElse(null);
    }

    // Create or update an order
    public Order_History saveOrder(Order_History orderHistory) {
        // Extract symbolId from the stock object inside the incoming order
        int symbolId = orderHistory.getStock().getSymbol_id();

        // Fetch stock entity from DB (foreign key)
        Stocks stock = stockRepository.findById(symbolId)
                .orElseThrow(() -> new RuntimeException("Stock with id " + symbolId + " not found"));

        // Attach managed stock entity to the order
        orderHistory.setStock(stock);

        // Save order
        return orderHistoryRepository.save(orderHistory);
    }

    // Delete an order by ID
    public void deleteOrder(int id) {
        orderHistoryRepository.deleteById(id);
    }
}
