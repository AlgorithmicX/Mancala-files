public class Player {
    private String name;
    private Store store;

    public Player() {
        this.name = "Player";
        this.store = new Store();
    }

    public Player(String name) {
        this.name = name;
        this.store = new Store();
    }

    public String getName() {
        return name;
    }

    public Store getStore() {
        return store;
    }

    public int getStoreCount() {
        return store.getTotalStones();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Player{" +
               "name='" + name + '\'' +
               ", store=" + store +
               '}';
    }
}

