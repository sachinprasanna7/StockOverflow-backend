package com.practice.StockOverflowBackend.Models;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // primary key

    private int symbolId;

    private int stockQuantity;

    private BigDecimal moneyInvested;

    // JPA requires a default constructor
    public Portfolio() {
    }

    public Portfolio(int symbolId, int stockQuantity, BigDecimal moneyInvested) {
        this.symbolId = symbolId;
        this.stockQuantity = stockQuantity;
        this.moneyInvested = moneyInvested;
    }

    public Long getId() {
        return id;
    }

    public int getSymbolId() {
        return symbolId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public BigDecimal getMoneyInvested() {
        return moneyInvested;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", symbolId=" + symbolId +
                ", stockQuantity=" + stockQuantity +
                ", moneyInvested=" + moneyInvested +
                '}';
    }
}
