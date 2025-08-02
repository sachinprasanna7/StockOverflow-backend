package com.practice.StockOverflowBackend;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.exceptions.BadRequestException;
import com.practice.StockOverflowBackend.exceptions.ResourceNotFoundException;
import com.practice.StockOverflowBackend.repositories.OrderHistoryRepository;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.services.OrderHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderHistoryServiceTest {

    private OrderHistoryRepository orderHistoryRepository;
    private StocksRepository stocksRepository;
    private OrderHistoryService service;

    @BeforeEach
    void setUp() {
        orderHistoryRepository = mock(OrderHistoryRepository.class);
        stocksRepository = mock(StocksRepository.class);
        service = new OrderHistoryService(orderHistoryRepository, stocksRepository);
    }

    @Test
    void saveOrder_shouldThrowBadRequest_ifStockIsNull() {
        Order_History order = new Order_History();
        order.setStock(null);

        assertThrows(BadRequestException.class, () -> service.saveOrder(order));
    }

    @Test
    void saveOrder_shouldThrowResourceNotFound_ifStockDoesNotExist() {
        Stocks stock = new Stocks();
        stock.setSymbolId(99);

        Order_History order = new Order_History();
        order.setStock(stock);

        when(stocksRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.saveOrder(order));
    }

    @Test
    void saveOrder_shouldThrowBadRequest_ifExecutedWithoutTimeCompleted() {
        Stocks stock = new Stocks();
        stock.setSymbolId(1);

        Order_History order = new Order_History();
        order.setStock(stock);
        order.setOrderStatus(Order_History.OrderStatusEnum.EXECUTED);
        order.setTimeOrdered(LocalDateTime.now());
        order.setTransactionAmount(BigDecimal.valueOf(100));

        when(stocksRepository.findById(1)).thenReturn(Optional.of(stock));

        assertThrows(BadRequestException.class, () -> service.saveOrder(order));
    }

    @Test
    void saveOrder_shouldClearTimeCompleted_ifStatusNotExecuted() {
        Stocks stock = new Stocks();
        stock.setSymbolId(1);

        Order_History order = new Order_History();
        order.setStock(stock);
        order.setOrderStatus(Order_History.OrderStatusEnum.PENDING);
        order.setTimeCompleted(LocalDateTime.now());
        order.setTransactionAmount(BigDecimal.valueOf(100));

        when(stocksRepository.findById(1)).thenReturn(Optional.of(stock));
        when(orderHistoryRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Order_History saved = service.saveOrder(order);

        assertNull(saved.getTimeCompleted(), "timeCompleted should be cleared for non-executed status");
    }

    @Test
    void saveOrder_shouldSaveSuccessfully_ifExecutedAndValid() {
        Stocks stock = new Stocks();
        stock.setSymbolId(1);

        Order_History order = new Order_History();
        order.setStock(stock);
        order.setOrderStatus(Order_History.OrderStatusEnum.EXECUTED);
        order.setTimeOrdered(LocalDateTime.now());
        order.setTimeCompleted(LocalDateTime.now());
        order.setTransactionAmount(BigDecimal.valueOf(100));

        when(stocksRepository.findById(1)).thenReturn(Optional.of(stock));
        when(orderHistoryRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Order_History saved = service.saveOrder(order);

        assertEquals(Order_History.OrderStatusEnum.EXECUTED, saved.getOrderStatus());
        assertNotNull(saved.getTimeCompleted());
    }
}
