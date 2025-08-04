package com.practice.StockOverflowBackend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.practice.StockOverflowBackend.compositeKeys.WatchlistStockCompositeKey;
import com.practice.StockOverflowBackend.dtos.WatchlistWithStocksDTO;
import com.practice.StockOverflowBackend.entities.Stocks;
import com.practice.StockOverflowBackend.entities.Watchlist;
import com.practice.StockOverflowBackend.entities.Watchlist_Stocks;
import com.practice.StockOverflowBackend.repositories.StocksRepository;
import com.practice.StockOverflowBackend.repositories.WatchlistRepository;
import com.practice.StockOverflowBackend.repositories.WatchlistStocksRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class WatchlistStocksServiceTest {

    @Mock
    private WatchlistStocksRepository watchlistStocksRepository;

    @Mock
    private StocksRepository stocksRepository;

    @Mock
    private WatchlistRepository watchlistRepository;

    @InjectMocks
    private WatchlistStocksService watchlistStocksService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // -------- getWatchlistStocksById tests --------

    @Test
    public void testGetWatchlistStocksById_Success() {
        int watchlistId = 1;
        Watchlist watchlist = new Watchlist();
        watchlist.setWatchlistId(watchlistId);
        watchlist.setWatchlistName("My Watchlist");

        Stocks stock = new Stocks();
        stock.setSymbolId(100);
        stock.setSymbol("AAPL");
        stock.setCompanyName("Apple Inc.");

        Watchlist_Stocks ws = new Watchlist_Stocks();
        ws.setStock(stock);

        when(watchlistRepository.findById(watchlistId)).thenReturn(Optional.of(watchlist));
        when(watchlistStocksRepository.findByCompositeKeyWatchlistId(watchlistId)).thenReturn(List.of(ws));

        WatchlistWithStocksDTO result = watchlistStocksService.getWatchlistStocksById(watchlistId);

        assertNotNull(result);
        assertEquals(watchlistId, result.getWatchlistId());
        assertEquals("My Watchlist", result.getWatchlistName());
        assertEquals(1, result.getStocks().size());
        assertEquals("AAPL", result.getStocks().get(0).getSymbol());
        assertEquals("Apple Inc.", result.getStocks().get(0).getCompanyName());
    }

    @Test
    public void testGetWatchlistStocksById_WatchlistNotFound() {
        int watchlistId = 999;

        when(watchlistRepository.findById(watchlistId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.getWatchlistStocksById(watchlistId);
        });

        assertEquals("404 NOT_FOUND \"Watchlist not found with ID: " + watchlistId + "\"", exception.getMessage());
    }

    @Test
    public void testGetWatchlistStocksById_UnexpectedException() {
        int watchlistId = 1;

        when(watchlistRepository.findById(watchlistId)).thenThrow(new RuntimeException("DB error"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.getWatchlistStocksById(watchlistId);
        });

        assertTrue(exception.getMessage().contains("Failed to get stocks for watchlist ID"));
        assertEquals(500, exception.getStatusCode().value());
    }

    // -------- addWatchlistStocks tests --------

    @Test
    public void testAddWatchlistStocks_Success() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 100);

        Stocks stock = new Stocks();
        stock.setSymbolId(100);

        Watchlist watchlist = new Watchlist();
        watchlist.setWatchlistId(1);

        when(stocksRepository.findById(100)).thenReturn(Optional.of(stock));
        when(watchlistRepository.findById(1)).thenReturn(Optional.of(watchlist));
        when(watchlistStocksRepository.existsByCompositeKey(key)).thenReturn(false);

        // No exception should be thrown
        assertDoesNotThrow(() -> watchlistStocksService.addWatchlistStocks(key));

        verify(watchlistStocksRepository, times(1)).save(any(Watchlist_Stocks.class));
    }

    @Test
    public void testAddWatchlistStocks_StockNotFound() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 999);

        when(stocksRepository.findById(999)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.addWatchlistStocks(key);
        });

        assertEquals(400, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("Stock does not exist"));
    }

    @Test
    public void testAddWatchlistStocks_WatchlistNotFound() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(999, 100);

        Stocks stock = new Stocks();
        stock.setSymbolId(100);

        when(stocksRepository.findById(100)).thenReturn(Optional.of(stock));
        when(watchlistRepository.findById(999)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.addWatchlistStocks(key);
        });

        assertEquals(400, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("Watchlist does not exist"));
    }

    @Test
    public void testAddWatchlistStocks_AlreadyExists() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 100);

        Stocks stock = new Stocks();
        stock.setSymbolId(100);

        Watchlist watchlist = new Watchlist();
        watchlist.setWatchlistId(1);

        when(stocksRepository.findById(100)).thenReturn(Optional.of(stock));
        when(watchlistRepository.findById(1)).thenReturn(Optional.of(watchlist));
        when(watchlistStocksRepository.existsByCompositeKey(key)).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.addWatchlistStocks(key);
        });

        assertEquals(409, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("Stock already exists in the watchlist"));
    }

    @Test
    public void testAddWatchlistStocks_UnexpectedException() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 100);

        when(stocksRepository.findById(100)).thenThrow(new RuntimeException("DB error"));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.addWatchlistStocks(key);
        });

        assertEquals(500, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("Failed to add stock to watchlist"));
    }

    // -------- deleteWatchlistStocksBySymbolId tests --------

    @Test
    public void testDeleteWatchlistStocksBySymbolId_Success() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 100);

        when(watchlistStocksRepository.existsByCompositeKey(key)).thenReturn(true);
        doNothing().when(watchlistStocksRepository).deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(1, 100);

        assertDoesNotThrow(() -> watchlistStocksService.deleteWatchlistStocksBySymbolId(key));

        verify(watchlistStocksRepository, times(1))
                .deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(1, 100);
    }

    @Test
    public void testDeleteWatchlistStocksBySymbolId_NotFound() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 100);

        when(watchlistStocksRepository.existsByCompositeKey(key)).thenReturn(false);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.deleteWatchlistStocksBySymbolId(key);
        });

        assertEquals(404, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("not found for deletion"));
    }

    @Test
    public void testDeleteWatchlistStocksBySymbolId_UnexpectedException() {
        WatchlistStockCompositeKey key = new WatchlistStockCompositeKey(1, 100);

        when(watchlistStocksRepository.existsByCompositeKey(key)).thenReturn(true);
        doThrow(new RuntimeException("DB error"))
                .when(watchlistStocksRepository).deleteByCompositeKeyWatchlistIdAndCompositeKeySymbolId(1, 100);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            watchlistStocksService.deleteWatchlistStocksBySymbolId(key);
        });

        assertEquals(500, ex.getStatusCode().value());
        assertTrue(ex.getReason().contains("Failed to delete stock from watchlist"));
    }
}
