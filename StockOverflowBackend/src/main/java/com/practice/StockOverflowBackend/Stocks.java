package com.practice.StockOverflowBackend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stocks {
    @Id
    private int symbol_id;
    private String symbol;
    private String company_name;

    public int getSymbol_id() {
        return symbol_id;
    }

    public void setSymbol_id(int symbol_id) {
        this.symbol_id = symbol_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
