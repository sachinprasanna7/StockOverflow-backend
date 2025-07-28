import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order_History {
    private int orderId;
    private LocalDateTime timeOrdered;
    private LocalDateTime timeCompleted;
    private int symbolId;
    public enum OrderTypeEnum {
        LIMIT,
        MARKET,
        STOP
    }
    public OrderTypeEnum orderType;
    private int stockQuantity;
    private BigDecimal transactionAmount;
    private boolean isBuy;
    private enum orderStatusEnum{
        PENDING,
        COMPLETED,
        CANCELLED
    }
    public orderStatusEnum orderStatus;


    public Order_History(int orderId, LocalDateTime timeOrdered, LocalDateTime timeCompleted,
                        int symbolId, OrderTypeEnum orderType, int stockQuantity,
                        BigDecimal transactionAmount, boolean isBuy, orderStatusEnum orderStatus) {
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

    // Getters and Setters omitted for brevity

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

public orderStatusEnum getOrderStatus() {
    return orderStatus;
}

@Override
public String toString() {
    return "Order_History{" +
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