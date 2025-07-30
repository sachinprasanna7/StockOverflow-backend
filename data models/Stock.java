public class Stock {
    private int symbolId;
    private String symbol;
    private String companyName;

    // Constructor
    public Stock(int symbolId, String symbol, String companyName) {
        this.symbolId = symbolId;
        this.symbol = symbol;
        this.companyName = companyName;
    }

    // Getters and Setters
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
