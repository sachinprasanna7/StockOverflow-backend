package com.practice.StockOverflowBackend.services;

import com.practice.StockOverflowBackend.entities.Portfolio;
import com.practice.StockOverflowBackend.repositories.PortfolioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    public void testBuyStock_NewEntry() {
        int symbolId = 1;
        int quantity = 10;
        BigDecimal price = BigDecimal.valueOf(50);
        BigDecimal investment = price.multiply(BigDecimal.valueOf(quantity));

        when(portfolioRepository.findById(symbolId)).thenReturn(Optional.empty());

        portfolioService.buyStock(symbolId, quantity, price);

        verify(portfolioRepository, times(1)).save(argThat(p ->
                p.getSymbolId() == symbolId &&
                        p.getStockQuantity() == quantity &&
                        p.getMoneyInvested().compareTo(investment) == 0
        ));
    }

    @Test
    public void testBuyStock_UpdateExisting() {
        int symbolId = 1;
        int quantity = 5;
        BigDecimal price = BigDecimal.valueOf(20);
        BigDecimal investment = price.multiply(BigDecimal.valueOf(quantity));

        Portfolio existing = new Portfolio(symbolId, 10, BigDecimal.valueOf(200));

        when(portfolioRepository.findById(symbolId)).thenReturn(Optional.of(existing));

        portfolioService.buyStock(symbolId, quantity, price);

        verify(portfolioRepository, times(1)).save(argThat(p ->
                p.getSymbolId() == symbolId &&
                        p.getStockQuantity() == 15 &&
                        p.getMoneyInvested().equals(BigDecimal.valueOf(200).add(investment))
        ));
    }

    @Test
    public void testSellStock_ValidSell() throws Exception {
        int symbolId = 1;
        int quantityToSell = 5;
        Portfolio existing = new Portfolio(symbolId, 10, BigDecimal.valueOf(100));

        when(portfolioRepository.findById(symbolId)).thenReturn(Optional.of(existing));

        portfolioService.sellStock(symbolId, quantityToSell, BigDecimal.valueOf(15));

        verify(portfolioRepository, times(1)).save(argThat(p ->
                p.getStockQuantity() == 5 &&
                        p.getMoneyInvested().compareTo(BigDecimal.valueOf(50)) == 0
        ));
    }

    @Test
    public void testSellStock_DeleteWhenZero() throws Exception {
        int symbolId = 1;
        int quantityToSell = 10;
        Portfolio existing = new Portfolio(symbolId, 10, BigDecimal.valueOf(200));

        when(portfolioRepository.findById(symbolId)).thenReturn(Optional.of(existing));

        portfolioService.sellStock(symbolId, quantityToSell, BigDecimal.valueOf(25));

        verify(portfolioRepository, times(1)).deleteById(symbolId);
    }

    @Test
    public void testSellStock_StockNotFound() {
        int symbolId = 999;

        when(portfolioRepository.findById(symbolId)).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () ->
                portfolioService.sellStock(symbolId, 5, BigDecimal.valueOf(20)));

        assertEquals("Stock not found in portfolio.", ex.getMessage());
    }

    @Test
    public void testSellStock_NotEnoughStock() {
        int symbolId = 1;
        Portfolio existing = new Portfolio(symbolId, 3, BigDecimal.valueOf(90));

        when(portfolioRepository.findById(symbolId)).thenReturn(Optional.of(existing));

        Exception ex = assertThrows(Exception.class, () ->
                portfolioService.sellStock(symbolId, 5, BigDecimal.valueOf(30)));

        assertEquals("Not enough stock to sell.", ex.getMessage());
    }
}