package com.practice.StockOverflowBackend.Models;

import java.time.LocalTime;

public class StockFeed {
    private String companyName;
    private int periodNumber;
    private double price;
    private String symbolString;
    private LocalTime timestamp;

    StockFeed(String companyName, int periodNumber,double price, String symbolString,LocalTime timestamp){
        this.companyName=companyName;
        this.periodNumber=periodNumber;
        this.price=price;
        this.symbolString=symbolString;
        this.timestamp=timestamp;

    }

    public String getCompanyName() {
        return companyName;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getSymbolString() {
        return symbolString;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

}

