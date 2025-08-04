package com.practice.StockOverflowBackend.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "order_history")
public class Order_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeOrdered;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol_id", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Stocks stock;

    public void setOrderId(int i) {
        this.orderId = i;
    }


    public enum OrderTypeEnum {
        LIMIT,
        MARKET,
        STOP
    }

    @Enumerated(EnumType.STRING)
    private OrderTypeEnum orderType;

    private int stockQuantity;
    private BigDecimal transactionAmount;
    private boolean isBuy;

    public enum OrderStatusEnum {
        PENDING,
        EXECUTED,
        CANCELLED
    }

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    // JPA requires a default constructor
    public Order_History() {}

    // Constructor for convenience
    public Order_History(
            LocalDateTime timeOrdered,
            LocalDateTime timeCompleted,
            Stocks stock,
            OrderTypeEnum orderType,
            int stockQuantity,
            BigDecimal transactionAmount,
            boolean isBuy,
            OrderStatusEnum orderStatus) {

        this.timeOrdered = timeOrdered;
        this.timeCompleted = timeCompleted;
        this.stock = stock;
        this.orderType = orderType;
        this.stockQuantity = stockQuantity;
        this.transactionAmount = transactionAmount;
        this.isBuy = isBuy;
        this.orderStatus = orderStatus;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(LocalDateTime timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public LocalDateTime getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(LocalDateTime timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeEnum orderType) {
        this.orderType = orderType;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order_History{" +
                "orderId=" + orderId +
                ", timeOrdered=" + timeOrdered +
                ", timeCompleted=" + timeCompleted +
                ", stock=" + (stock != null ? stock.getSymbol_id() : null) +
                ", orderType=" + orderType +
                ", stockQuantity=" + stockQuantity +
                ", transactionAmount=" + transactionAmount +
                ", isBuy=" + isBuy +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
