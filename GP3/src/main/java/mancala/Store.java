package mancala;

public class Store {
    private Player storeOwner;
    private int noOfStonesInMancala;

    public Store(){
        noOfStonesInMancala = 0;
    }
    
    public void addStones(int amount){
        noOfStonesInMancala = noOfStonesInMancala + amount;
    }
    public int emptyStore(){
        int clearedStones = noOfStonesInMancala;
        noOfStonesInMancala = 0;
        return clearedStones;
    }

    public Player getOwner(){
        return storeOwner;
    }
    public int getTotalStones(){
        return noOfStonesInMancala;
    }
    public void setOwner(Player player){
        this.storeOwner = player;
    }

    public void removeStones(){
        noOfStonesInMancala = 0;
    }
    @Override
    public String toString(){
        return "Store belonging to" + storeOwner + " currently contains " + noOfStonesInMancala + " stones.";
    }
}
