public class Pit {
    private int stones;
    private Player owner; // Add owner attribute to represent the owner of the pit

    public Pit() {
        this.stones = 0;
    }

    public Pit(int initialStones) {
        this.stones = initialStones;
    }

    

    public void addStone() {
        stones++;
    }

    public void removeStones() {
        stones = 0;
    }

    public int getStoneCount() {
        return stones;
    }

    public Player getOwner() {
        return owner; // Get the owner of the pit
    }

    @Override
    public String toString() {
        return Integer.toString(stones);
    }
}
