package com.practice.StockOverflowBackend.repositories;

import com.practice.StockOverflowBackend.entities.Order_History;
import com.practice.StockOverflowBackend.entities.Stocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderHistoryRepositoryTest {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private StocksRepository stocksRepository;

    private Stocks createStock(String symbol, String companyName) {
        Stocks stock = new Stocks();
        stock.setSymbolId(1); // primary key
        stock.setSymbol(symbol);
        stock.setCompanyName(companyName);
        return stocksRepository.save(stock);
    }

    private Order_History createOrder(Stocks stock) {
        Order_History order = new Order_History();
        order.setStock(stock);
        order.setOrderType(Order_History.OrderTypeEnum.LIMIT);
        order.setStockQuantity(10);
        order.setTransactionAmount(BigDecimal.valueOf(1000));
        order.setOrderStatus(Order_History.OrderStatusEnum.EXECUTED);
        order.setTimeOrdered(LocalDateTime.now().minusHours(1));
        order.setTimeCompleted(LocalDateTime.now());
        return orderHistoryRepository.save(order);
    }

    @Test
    @DisplayName("Search by stock company name or symbol should return matching orders")
    void testSearchByStockNameOrSymbol() {
        // Arrange
        Stocks stock = createStock("TATA", "Tata Motors");
        createOrder(stock);

        // Act
        List<Order_History> byCompanyName =
                orderHistoryRepository.searchByStockNameOrSymbol("Tata");
        List<Order_History> bySymbol =
                orderHistoryRepository.searchByStockNameOrSymbol("TATA");

        // Assert
        assertThat(byCompanyName).hasSize(1);
        assertThat(byCompanyName.get(0).getStock().getCompanyName()).isEqualTo("Tata Motors");

        assertThat(bySymbol).hasSize(1);
        assertThat(bySymbol.get(0).getStock().getSymbol()).isEqualTo("TATA");
    }

    @Test
    @DisplayName("Search with no matching keyword should return empty list")
    void testSearchByStockNameOrSymbol_NoResults() {
        Stocks stock = createStock("INFY", "Infosys");
        createOrder(stock);

        List<Order_History> result = orderHistoryRepository.searchByStockNameOrSymbol("Apple");
        assertThat(result).isEmpty();
    }
}
