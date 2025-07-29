public class Stock {
    private int symbolId;
    private String symbol;
    private String companyName;

    // Constructor
    Stock(int symbolId, String symbol, String companyName) {
        this.symbolId = symbolId;
        this.symbol = symbol;
        this.companyName = companyName;
    }

    // Getters and Setters
    public int getSymbolId() { return symbolId; }
    public void setSymbolId(int symbolId) { this.symbolId = symbolId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    @Override
    public String toString() {
        return "Stock{" +
                "symbolId=" + symbolId +
                ", symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
