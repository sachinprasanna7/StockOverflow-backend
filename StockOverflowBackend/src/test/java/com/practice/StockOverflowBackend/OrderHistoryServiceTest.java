package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.exceptions.BadRequestException;
import com.practice.StockOverflowBackend.exceptions.ResourceNotFoundException;
import com.practice.StockOverflowBackend.repositories.OrderHistoryRepository;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderHistoryServiceTest {

    @Mock
    private OrderHistoryRepository orderHistoryRepository;

    @Mock
    private StocksRepository stocksRepository;

    @InjectMocks
    private OrderHistoryService orderHistoryService;

    private Order_History createValidOrder() {
        Stocks stock = new Stocks();
        stock.setSymbolId(1);

        Order_History order = new Order_History();
        order.setStock(stock);
        order.setOrderType(Order_History.OrderTypeEnum.LIMIT);
        order.setStockQuantity(10);
        order.setTransactionAmount(BigDecimal.valueOf(1000));
        order.setTimeOrdered(LocalDateTime.now().minusHours(1));
        order.setTimeCompleted(LocalDateTime.now());
        order.setOrderStatus(Order_History.OrderStatusEnum.EXECUTED);

        return order;
    }

    @Test
    public void testSaveOrder_Success() {
        Order_History order = createValidOrder();

        when(stocksRepository.findById(1)).thenReturn(Optional.of(order.getStock()));
        when(orderHistoryRepository.save(order)).thenReturn(order);

        Order_History result = orderHistoryService.saveOrder(order);

        assertNotNull(result);
        verify(orderHistoryRepository, times(1)).save(order);
    }

    @Test
    public void testSaveOrder_MissingStock() {
        Order_History order = createValidOrder();
        order.setStock(null);

        BadRequestException ex = assertThrows(BadRequestException.class,
                () -> orderHistoryService.saveOrder(order));
        assertEquals("Stock must be provided in the order.", ex.getMessage());
    }

    @Test
    public void testSaveOrder_InvalidStockId() {
        Order_History order = createValidOrder();

        when(stocksRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> orderHistoryService.saveOrder(order));
    }

    @Test
    public void testSaveOrder_NegativeQuantity() {
        Order_History order = createValidOrder();
        order.setStockQuantity(-5);

        when(stocksRepository.findById(1)).thenReturn(Optional.of(order.getStock()));

        BadRequestException ex = assertThrows(BadRequestException.class,
                () -> orderHistoryService.saveOrder(order));
        assertEquals("Stock quantity must be positive.", ex.getMessage());
    }

    @Test
    public void testSaveOrder_InvalidCompletedTime() {
        Order_History order = createValidOrder();
        order.setTimeCompleted(order.getTimeOrdered().minusHours(2));

        when(stocksRepository.findById(1)).thenReturn(Optional.of(order.getStock()));

        BadRequestException ex = assertThrows(BadRequestException.class,
                () -> orderHistoryService.saveOrder(order));
        assertEquals("timeCompleted cannot be before timeOrdered.", ex.getMessage());
    }

    @Test
    public void testGetOrderById_NotFound() {
        when(orderHistoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> orderHistoryService.getOrderById(1));
    }

    @Test
    public void testGetOrderById_Found() {
        Order_History order = createValidOrder();
        when(orderHistoryRepository.findById(1)).thenReturn(Optional.of(order));

        Order_History result = orderHistoryService.getOrderById(1);

        assertNotNull(result);
        assertEquals(order, result);
    }
}
