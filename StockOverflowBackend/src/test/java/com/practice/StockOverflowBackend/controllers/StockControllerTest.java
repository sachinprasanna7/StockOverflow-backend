package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.services.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StocksControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StockService stockService;

    @InjectMocks
    private StocksController stocksController;

    private Stocks stock1;
    private Stocks stock2;

    @BeforeEach
    void setUp() {
        stock1 = new Stocks(1, "AAPL", "Apple Inc.");
        stock2 = new Stocks(2, "GOOGL", "Alphabet Inc.");
        mockMvc = MockMvcBuilders.standaloneSetup(stocksController).build();
    }

    @Test
    void testGetAllStocks() throws Exception {
        when(stockService.getStocks()).thenReturn(Arrays.asList(stock1, stock2));

        mockMvc.perform(get("/api/stock/getStocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$[1].symbol").value("GOOGL"));
    }

    @Test
    void testGetStockById_StockFound() throws Exception {
        when(stockService.getStockById(1)).thenReturn(stock1);

        mockMvc.perform(get("/api/stock/{stock_id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value("AAPL"))
                .andExpect(jsonPath("$.companyName").value("Apple Inc."));
    }

    @Test
    void testGetStockById_StockNotFound() throws Exception {
        when(stockService.getStockById(3)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/api/stock/{stock_id}", 3))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

    @Test
    void testSearchStocks_Found() throws Exception {
        when(stockService.searchStocks("apple")).thenReturn(Arrays.asList(stock1));

        mockMvc.perform(get("/api/stock/search?query=apple"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].symbol").value("AAPL"));
    }

    @Test
    void testSearchStocks_NotFound() throws Exception {
        when(stockService.searchStocks("amazon")).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/stock/search?query=amazon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}