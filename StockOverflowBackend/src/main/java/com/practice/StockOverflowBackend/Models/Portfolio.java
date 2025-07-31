package com.practice.StockOverflowBackend.Models;
import java.math.BigDecimal;
import jakarta.persistance.Entity;


public class Portfolio {
    private int symbolId;
    private int stockQuantity;
    private BigDecimal moneyInvested;

    Portfolio(int symbolId, int stockQuantity, BigDecimal moneyInvested) {
        this.symbolId = symbolId;
        this.stockQuantity = stockQuantity;
        this.moneyInvested = moneyInvested;
    }

    public int getSymbolId() { return symbolId; }

    public int getStockQuantity() { return stockQuantity; }

    public BigDecimal getMoneyInvested() { return moneyInvested; }

    @Override
    public String toString() {
        return "Portfolio{" +
                "symbolId=" + symbolId +
                ", stockQuantity=" + stockQuantity +
                ", moneyInvested=" + moneyInvested +
                '}';
    }
}
