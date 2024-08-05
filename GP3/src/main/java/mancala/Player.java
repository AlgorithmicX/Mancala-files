package mancala;

public class Player {
    private String playerName;
    private Store playerMancala;

    public Player(){
        this.playerName = "";
        this.playerMancala = new Store();
    }
    
    public Player(String name){
        this.playerName = name;
        this.playerMancala = new Store();
    }

    public String getName(){
        return playerName;
    }
    public Store getStore(){
        return playerMancala;
    }
    public int getStoreCount(){
        return playerMancala.getTotalStones();
    }
    public void setName(String name){
        this.playerName = name;
    }
    public void setStore(Store store){
        this.playerMancala = store;
    }
    @Override
    public String toString(){
        return "Player: " + playerName;
    }
}
