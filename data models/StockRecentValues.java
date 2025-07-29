import java.time.LocalTime;
import java.util.*;

public class StockRecentValues {
    private String companyName;
    private String symbol;
    private List<Integer>periodNumbers=new ArrayList<>();
    private List<Double> price=new ArrayList<>();
    private List<LocalTime> timestamps=new ArrayList<>();
    StockRecentValues(StockFeed[] NstockValues){
        //Handle Error for 0
        this.companyName=NstockValues[0].getCompanyName();
        this.symbol=NstockValues[0].getSymbolString();
        for(StockFeed stockInstance: NstockValues){
            periodNumbers.add(stockInstance.getPeriodNumber());
            price.add(stockInstance.getPrice());
            timestamps.add(stockInstance.getTimestamp());
        }


    }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    
}
