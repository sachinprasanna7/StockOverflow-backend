package com.practice.StockOverflowBackend.entities;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @Column(name="symbol_id")
    private int symbolId;
    @Column(name="stock_quantity", nullable = false)
    private int stockQuantity;
    @Column(name="money_invested", nullable = false)
    private BigDecimal averagePrice; // changed from moneyInvested to averagePrice

    // JPA requires a default constructor
    public Portfolio() {
    }

    public Portfolio(int symbolId, int stockQuantity, BigDecimal averagePrice) {
        this.symbolId = symbolId;
        this.stockQuantity = stockQuantity;
        this.averagePrice = averagePrice;
    }

    // Getters for JPA
    public int getSymbolId() {
        return symbolId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public BigDecimal getaveragePrice() {
        return averagePrice;
    }

    // Setters for JPA
    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setaveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "symbolId=" + symbolId +
                ", stockQuantity=" + stockQuantity +
                ", Average Stock Value=" + averagePrice +
                '}';
    }
}
