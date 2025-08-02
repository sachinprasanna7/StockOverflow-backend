package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.exceptions.BadRequestException;
import com.practice.StockOverflowBackend.exceptions.ResourceNotFoundException;
import com.practice.StockOverflowBackend.repositories.OrderHistoryRepository;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import org.springframework.stereotype.Service;

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
        if (orderHistory.getStock() == null) {
            throw new BadRequestException("Stock must be provided in the order.");
        }

        int symbolId = orderHistory.getStock().getSymbol_id();

        Stocks stock = stockRepository.findById(symbolId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock with id " + symbolId + " not found"));

        orderHistory.setStock(stock);

        // ✅ Validate timeCompleted based on order status
        if (orderHistory.getOrderStatus() == Order_History.OrderStatusEnum.EXECUTED) {
            if (orderHistory.getTimeCompleted() == null) {
                throw new BadRequestException("timeCompleted must be provided when orderStatus is EXECUTED.");
            }
        } else {
            // Any status other than EXECUTED → timeCompleted must be null
            orderHistory.setTimeCompleted(null);
        }

        return orderHistoryRepository.save(orderHistory);
    }


}
