package com.practice.StockOverflowBackend.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    private int symbolId;  // primary key (not auto-generated here)

    private String symbol;

    private String companyName;

    // JPA requires a public no-args constructor
    public Stock() {
    }

    // Constructor for convenience
    public Stock(int symbolId, String symbol, String companyName) {
        this.symbolId = symbolId;
        this.symbol = symbol;
        this.companyName = companyName;
    }

    public int getSymbolId() { return symbolId; }

    public String getSymbol() { return symbol; }

    public String getCompanyName() { return companyName; }

    @Override
    public String toString() {
        return "Stock{" +
                "symbolId=" + symbolId +
                ", symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
