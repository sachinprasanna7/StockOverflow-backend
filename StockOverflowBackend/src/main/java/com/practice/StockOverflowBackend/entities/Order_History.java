package com.practice.StockOverflowBackend.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_history")
public class Order_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private LocalDateTime timeOrdered;
    private LocalDateTime timeCompleted;
    private int symbolId;

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

    // Default constructor required by JPA
    public Order_History() {
    }

    public Order_History(int orderId,
                        LocalDateTime timeOrdered,
                        LocalDateTime timeCompleted,
                        int symbolId,
                        OrderTypeEnum orderType,
                        int stockQuantity,
                        BigDecimal transactionAmount,
                        boolean isBuy,
                        OrderStatusEnum orderStatus) {
        this.orderId = orderId;
        this.timeOrdered = timeOrdered;
        this.timeCompleted = timeCompleted;
        this.symbolId = symbolId;
        this.orderType = orderType;
        this.stockQuantity = stockQuantity;
        this.transactionAmount = transactionAmount;
        this.isBuy = isBuy;
        this.orderStatus = orderStatus;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getTimeOrdered() {
        return timeOrdered;
    }

    public LocalDateTime getTimeCompleted() {
        return timeCompleted;
    }

    public int getSymbolId() {
        return symbolId;
    }

    public OrderTypeEnum getOrderType() {
        return orderType;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderId=" + orderId +
                ", timeOrdered=" + timeOrdered +
                ", timeCompleted=" + timeCompleted +
                ", symbolId=" + symbolId +
                ", orderType=" + orderType +
                ", stockQuantity=" + stockQuantity +
                ", transactionAmount=" + transactionAmount +
                ", isBuy=" + isBuy +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
