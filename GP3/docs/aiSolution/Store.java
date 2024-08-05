public class Store {
    private int totalStones;
    private Player owner;

    public Store() {
        this.totalStones = 0;
        this.owner = null;
    }

    public void addStones(int amount) {
        totalStones += amount;
    }

    public void emptyStore() {
        totalStones = 0;
    }

    public Player getOwner() {
        return owner;
    }

    public int getTotalStones() {
        return totalStones;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public String toString() {
        return "Store{" +
               "totalStones=" + totalStones +
               ", owner=" + owner +
               '}';
    }
}

