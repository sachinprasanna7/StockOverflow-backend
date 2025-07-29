public class MinMaxPeriod {
    private double closingPrice;
    private double maxPrice;
    private double minPrice;
    private double openingPrice;
    private String periodEndTime;
    private int periodNumber;
    private String periodStartTime;
    private String symbol;
    private int symbolID;

    public MinMaxPeriod(double closingPrice, double maxPrice, double minPrice, double openingPrice,
                        String periodEndTime, int periodNumber, String periodStartTime,
                        String symbol, int symbolID) {
        this.closingPrice = closingPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.openingPrice = openingPrice;
        this.periodEndTime = periodEndTime;
        this.periodNumber = periodNumber;
        this.periodStartTime = periodStartTime;
        this.symbol = symbol;
        this.symbolID = symbolID;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public String getPeriodEndTime() {
        return periodEndTime;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public String getPeriodStartTime() {
        return periodStartTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSymbolID() {
        return symbolID;
    }
}
