package com.practice.StockOverflowBackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.services.OrderHistoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderHistoryController.class)
public class OrderHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderHistoryService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Order_History createSampleOrder() {
        Stocks stock = new Stocks();
        stock.setSymbolId(10);

        Order_History order = new Order_History();
        order.setOrderId(1);
        order.setStock(stock);
        order.setOrderType(Order_History.OrderTypeEnum.LIMIT);
        order.setStockQuantity(20);
        order.setTransactionAmount(BigDecimal.valueOf(1000));
        order.setOrderStatus(Order_History.OrderStatusEnum.EXECUTED);
        order.setTimeOrdered(LocalDateTime.now().minusHours(1));
        order.setTimeCompleted(LocalDateTime.now());
        return order;
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Order_History order = createSampleOrder();

        Mockito.when(service.getAllOrders()).thenReturn(Collections.singletonList(order));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Total-Count", "1"))
                .andExpect(jsonPath("$[0].orderId").value(order.getOrderId()));
    }

    @Test
    public void testGetOrderById_Found() throws Exception {
        Order_History order = createSampleOrder();
        Mockito.when(service.getOrderById(1)).thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Order-Found", "true"))
                .andExpect(jsonPath("$.orderId").value(order.getOrderId()));
    }

    @Test
    public void testGetOrderById_NotFound() throws Exception {
        Mockito.when(service.getOrderById(99)).thenReturn(null);

        mockMvc.perform(get("/orders/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order_History order = createSampleOrder();

        Mockito.when(service.saveOrder(any(Order_History.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(header().string("X-Created-Id", "1"))
                .andExpect(jsonPath("$.orderId").value(order.getOrderId()));
    }

    @Test
    public void testSearchOrdersByStockName() throws Exception {
        Order_History order = createSampleOrder();
        Mockito.when(service.searchOrdersByStockNameOrSymbol("TATA"))
                .thenReturn(Arrays.asList(order));

        mockMvc.perform(get("/orders/search")
                        .param("name", "TATA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(order.getOrderId()));
    }
}
