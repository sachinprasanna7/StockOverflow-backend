package com.practice.StockOverflowBackend.entities;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @Column(name="symbol_id")
    private int symbolId;
    @Column(name="stock_quantity")
    private int stockQuantity;
    @Column(name="money_invested")
    private BigDecimal moneyInvested;

    // JPA requires a default constructor
    public Portfolio() {
    }

    public Portfolio(int symbolId, int stockQuantity, BigDecimal moneyInvested) {
        this.symbolId = symbolId;
        this.stockQuantity = stockQuantity;
        this.moneyInvested = moneyInvested;
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

                ", symbolId=" + symbolId +
                ", stockQuantity=" + stockQuantity +
                ", moneyInvested=" + moneyInvested +
                '}';
    }
}
