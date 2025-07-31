package com.practice.StockOverflowBackend.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "stock_history")
public class Stock_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int symbolId;

    private int periodNumber;

    private LocalTime periodStartTime;

    private LocalTime periodEndTime;

    private BigDecimal openingPrice;

    private BigDecimal closingPrice;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    // Default constructor required by JPA
    public Stock_History() {
    }

    // Parameterized constructor
    public Stock_History(int id, int symbolId, int periodNumber,
                        LocalTime periodStartTime, LocalTime periodEndTime,
                        BigDecimal openingPrice, BigDecimal closingPrice,
                        BigDecimal minPrice, BigDecimal maxPrice) {
        this.id = id;
        this.symbolId = symbolId;
        this.periodNumber = periodNumber;
        this.periodStartTime = periodStartTime;
        this.periodEndTime = periodEndTime;
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getId() {
        return id;
    }

    public int getSymbolId() {
        return symbolId;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public LocalTime getPeriodStartTime() {
        return periodStartTime;
    }

    public LocalTime getPeriodEndTime() {
        return periodEndTime;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    @Override
    public String toString() {
        return "StockHistory{" +
                "id=" + id +
                ", symbolId=" + symbolId +
                ", periodNumber=" + periodNumber +
                ", periodStartTime=" + periodStartTime +
                ", periodEndTime=" + periodEndTime +
                ", openingPrice=" + openingPrice +
                ", closingPrice=" + closingPrice +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
