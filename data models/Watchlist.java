public class Watchlist {
    private int watchlistId;
    private String name;

    Watchlist(int watchlistId, String name) {
        this.watchlistId = watchlistId;
        this.name = name;
    }

    public int getWatchlistId() { return watchlistId; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Watchlist{" +
                "watchlistId=" + watchlistId +
                ", name='" + name + '\'' +
                '}';
    }
}
