package com.practice.StockOverflowBackend;

import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.services.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @Mock
    private StocksRepository stocksRepository;

    @InjectMocks
    private StockService stockService;

    private Stocks stock1;
    private Stocks stock2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stock1 = new Stocks(1, "AAPL", "Apple Inc.");
        stock2 = new Stocks(2, "GOOGL", "Alphabet Inc.");
    }

    @Test
    void testGetStocks() {
        when(stocksRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<Stocks> stocks = stockService.getStocks();

        assertEquals(2, stocks.size());
        assertEquals("AAPL", stocks.get(0).getSymbol());
        assertEquals("GOOGL", stocks.get(1).getSymbol());
    }

    @Test
    void testGetStockById_StockFound() {
        when(stocksRepository.findById(1)).thenReturn(Optional.of(stock1));

        Stocks result = stockService.getStockById(1);

        assertNotNull(result);
        assertEquals("AAPL", result.getSymbol());
    }

    @Test
    void testGetStockById_StockNotFound() {
        when(stocksRepository.findById(3)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> stockService.getStockById(3));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getMessage().contains("Stock with ID: 3 not found"));
    }

    @Test
    void testSearchStocks_Found() {
        when(stocksRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<Stocks> result = stockService.searchStocks("apple");

        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
    }

    @Test
    void testSearchStocks_NotFound() {
        when(stocksRepository.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<Stocks> result = stockService.searchStocks("amazon");

        assertEquals(0, result.size());
    }
}
