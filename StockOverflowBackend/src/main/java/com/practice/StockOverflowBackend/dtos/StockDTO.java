package com.practice.StockOverflowBackend.dtos;

public class StockDTO {
    private int symbolId;
    private String symbol;
    private String companyName;

    public StockDTO() {}

    public StockDTO(int symbolId, String symbol, String companyName) {
        this.symbolId = symbolId;
        this.symbol = symbol;
        this.companyName = companyName;
    }

    public int getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
