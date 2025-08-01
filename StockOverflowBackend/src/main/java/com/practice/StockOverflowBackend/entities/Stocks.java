package com.practice.StockOverflowBackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stocks {

    @Id
    private int symbol_id;  // primary key (not auto-generated here)

    private String symbol;
    @Column(name="company_name")
    private String companyName;

    // JPA requires a public no-args constructor
    public Stocks() {
    }

    // Constructor for convenience
    public Stocks(int symbol_id, String symbol, String companyName) {
        this.symbol_id = symbol_id;
        this.symbol = symbol;
        this.companyName = companyName;
    }

    public int getSymbol_id() { return symbol_id; }

    public String getSymbol() { return symbol; }

    public String getCompanyName() { return companyName; }

    @Override
    public String toString() {
        return "Stock{" +
                "symbolId=" + symbol_id +
                ", symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
