package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.exceptions.BadRequestException;
import com.practice.StockOverflowBackend.exceptions.ResourceNotFoundException;
import com.practice.StockOverflowBackend.repositories.OrderHistoryRepository;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;
    private final StocksRepository stockRepository;

    public OrderHistoryService(OrderHistoryRepository orderHistoryRepository,
                               StocksRepository stockRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.stockRepository = stockRepository;
    }

    public List<Order_History> getAllOrders() {
        return orderHistoryRepository.findAll();
    }

    public Order_History getOrderById(int id) {
        return orderHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public Order_History saveOrder(Order_History orderHistory) {
        // Stock must be provided
        if (orderHistory.getStock() == null) {
            throw new BadRequestException("Stock must be provided in the order.");
        }

        int symbolId = orderHistory.getStock().getSymbol_id();

        Stocks stock = stockRepository.findById(symbolId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock with id " + symbolId + " not found"));

        orderHistory.setStock(stock);

        // Validate orderType - must not be null
        if (orderHistory.getOrderType() == null) {
            throw new BadRequestException("Order type must be provided.");
        }

        // Validate stockQuantity - must be positive
        if (orderHistory.getStockQuantity() <= 0) {
            throw new BadRequestException("Stock quantity must be positive.");
        }

        // Validate transactionAmount - must be positive
        if (orderHistory.getTransactionAmount() == null || orderHistory.getTransactionAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Transaction amount must be positive.");
        }

        // Validate isBuy - (assuming it cannot be null, primitive boolean - no check needed)

        // Validate timeOrdered - must not be null and cannot be in the future (optional)
        if (orderHistory.getTimeOrdered() == null) {
            throw new BadRequestException("timeOrdered must be provided.");
        }
        if (orderHistory.getTimeOrdered().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("timeOrdered cannot be in the future.");
        }

        // Validate timeCompleted based on orderStatus
        if (orderHistory.getOrderStatus() == null) {
            throw new BadRequestException("Order status must be provided.");
        }

        if (orderHistory.getOrderStatus() == Order_History.OrderStatusEnum.EXECUTED) {
            // timeCompleted must be provided
            if (orderHistory.getTimeCompleted() == null) {
                throw new BadRequestException("timeCompleted must be provided when orderStatus is EXECUTED.");
            }
            // timeCompleted should NOT be before timeOrdered
            if (orderHistory.getTimeCompleted().isBefore(orderHistory.getTimeOrdered())) {
                throw new BadRequestException("timeCompleted cannot be before timeOrdered.");
            }
        } else {
            // Any other status → timeCompleted must be null
            orderHistory.setTimeCompleted(null);
        }

        return orderHistoryRepository.save(orderHistory);
    }

}
