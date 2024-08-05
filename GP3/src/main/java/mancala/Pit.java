package mancala;

public class Pit {
    private int noOfStonesInPit;
   

    public Pit(){
        noOfStonesInPit = 0;
    }
    
    public void addStone(){
        noOfStonesInPit++;
    }

    public int getStoneCount(){
        return noOfStonesInPit;
    }

    public int removeStones(){
        int stonesRemoved = noOfStonesInPit;
        noOfStonesInPit = 0;
        return stonesRemoved;
    }

    @Override
    public String toString(){
        return "Pit contains --" + noOfStonesInPit+" stones";
    }
}
