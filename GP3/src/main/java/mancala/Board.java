package mancala;

import java.util.ArrayList;

public class Board{
    private ArrayList<Pit> pits;
    private ArrayList<Store> mancala;
    private Player currentPlayer;
    private boolean extraTurn;
    

    public Board(){
        pits = new ArrayList<>();
        mancala = new ArrayList<>();
        setUpPits();
        setUpStores();
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    
    
    public int captureStones(int stoppingPoint) throws PitNotFoundException {
        if(stoppingPoint < 1 || stoppingPoint > 12){
            throw new PitNotFoundException("invalid pit.");
        }
        int stonesAtStoppingPoint = pits.get(stoppingPoint).removeStones();
        int opponentPitIndex = 0;
         opponentPitIndex = 11 - stoppingPoint;
         int stonesAtOpponentPitIndex = pits.get(opponentPitIndex).removeStones();
         if(stonesAtOpponentPitIndex > 0){
            int stonesToAdd = stonesAtStoppingPoint + stonesAtOpponentPitIndex;
            System.out.println("CAPTURE SUCCESSFUL!");
            currentPlayer.getStore().addStones(stonesToAdd);
            return stonesToAdd;
            //return stonesAtOpponentPitIndex;
         }else {
            return 0; 
        }
    }
    public void validatePit(int start) throws PitNotFoundException{
        if(start < 1 || start > 12){
            throw new PitNotFoundException("Invalid pit");
        }
    }

    public int distributeStones(int startingPoint) throws PitNotFoundException{
        validatePit(startingPoint);
       extraTurn = false;
       int pitStart = startingPoint - 1;
       int stones = pits.get(pitStart).removeStones();
       int stonesDistributed = stones;
       int index = pitStart;
        while(stones > 0){
         if(startingPoint >= 1 && startingPoint <= 6){
            if(pitStart == 5){
                handleForPlayer(stones);
                stones--;
                pitStart--;
            }
            index = index + 1;
            pits.get(index).addStone();
            if(index == 5){
                stones--;
                handleStore(stones);
                if(stones > 0){
                    currentPlayer.getStore().addStones(1);
                }
            }else if(index == 11){
                index = 0;
            }
            stones--;
        } else if(startingPoint >= 7 &&  startingPoint <= 12){
            if(pitStart == 11){
                handleForPlayer(stones);
                stones--;
                pitStart--;
                index = -1;
            }
            if(stones > 0){
                index = index + 1;
                pits.get(index).addStone();
                stones--;
                if(index == 11){
                    handleStore(stones);
                    if(stones>0){
                        currentPlayer.getStore().addStones(1);
                        stones--;
                    }
                    index = -1;
                }
            }
        }
    }
        return stonesDistributed;   
    }
    public void handleStore(int stones){
        if(stones == 1){
            extraTurn = true;
        }
    }

    public void handleForPlayer(int stones){
        if(stones == 1){
            extraTurn = true;
        }
        currentPlayer.getStore().addStones(1);
        
    }

    public boolean hasExtraTurn() {
        return extraTurn;
    }

    public int moveStones(int startPit, Player player) throws InvalidMoveException{
        if(startPit < 1 || startPit > 12){
            throw new InvalidMoveException("invalid move");
        }
        currentPlayer = player;
        int stones = 0;
        int indexOfStoppingPoint = 0;
        int stonesAtStoppingPoint = 0;
        try{
            stones = distributeStones(startPit);
        }catch (PitNotFoundException e){
           System.out.println(e.getMessage());
        }
        if(startPit >=1 && startPit <=6){
            if(stones > (6-startPit)){
                indexOfStoppingPoint = (startPit + stones - 2)%13;
            }else{
                indexOfStoppingPoint = (startPit + stones - 1)%13;
            }
            if(indexOfStoppingPoint >= 0 && indexOfStoppingPoint <=5){
                stonesAtStoppingPoint = pits.get(indexOfStoppingPoint).getStoneCount();
                int opponentPit = 11 - indexOfStoppingPoint;
                int opponentStones = pits.get(opponentPit).getStoneCount();
                if(stonesAtStoppingPoint == 1 && opponentStones > 0){
                    try{
                        captureStones(indexOfStoppingPoint);
                    }catch (PitNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                }
            } 
        }else{
            if(startPit >=7 && startPit <=12){
                indexOfStoppingPoint = (startPit+stones -1)%13;
            }
            if(indexOfStoppingPoint >= 6 && indexOfStoppingPoint <= 11){
                stonesAtStoppingPoint = pits.get(indexOfStoppingPoint).getStoneCount();
                int opponentPit = 11 - indexOfStoppingPoint;
                int opponentStones = pits.get(opponentPit).getStoneCount();
                if(stonesAtStoppingPoint == 1 && opponentStones > 0){
                    try{
                        captureStones(indexOfStoppingPoint);
                    }catch (PitNotFoundException e){
                        System.out.println(e.getMessage());
                    } 
                }
            }
        }
        return currentPlayer.getStoreCount();
    }

    public int getNumStones(int pitNum) throws PitNotFoundException{
        if(pitNum < 1 || pitNum > 12){
            throw new PitNotFoundException("Pit number is invalid.");
        }
        int index = pitNum - 1;
        Pit temp = pits.get(index);
        int stones = temp.getStoneCount();
        //System.out.println("number of stones at index: " + index + "is :" + stones);
        return stones;
    }

    public ArrayList<Pit> getPits(){
        return pits;
    }

    public ArrayList<Store> getStores(){
        return mancala;
    }

    public void initializeBoard(){
        /*initializes the board by distributing stones */
        int stonesForAPit = 4;
        for(Pit pit: pits){
            for(int j=0; j < stonesForAPit; j++){
                pit.addStone();
            }
        }
    }
    
    public boolean isSideEmpty(int pitNum) throws PitNotFoundException{
        if(pitNum < 1 || pitNum > 12){
            throw new PitNotFoundException("Pit number is invalid.");
        }
        if(pitNum >= 1 && pitNum <= 6){
            for(int i=0; i < 6; i++){
                if(pits.get(i).getStoneCount() > 0){
                    return false;
                }
            }
        }else if(pitNum >= 7 && pitNum <= 12){
            for(int i=6; i < 12; i++){
                if(pits.get(i).getStoneCount() > 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void registerPlayers(Player one, Player two){
        /*connects players to their stores */
        one.setStore(mancala.get(0));
        two.setStore(mancala.get(1));
        mancala.get(0).setOwner(one);
        mancala.get(1).setOwner(two);
    }
    
    
    public boolean arePitsEmpty(){
        for(Pit pit:pits){
            if(pit.getStoneCount() > 0){
                return false;
            }
        }
        return true;
    }
    public boolean areStoresEmpty(){
        for(Store store:mancala){
            if(store.getTotalStones() > 0){
                return false;
            }
        }
        return true;
    }

   
    public void resetBoard(){
        for(int i=0; i < 2; i++){
            mancala.get(i).emptyStore();
        }
        for(int i=0; i < 12; i++){
            pits.get(i).removeStones();
        }
        initializeBoard();
    }

    public void setUpPits(){
        //pits.clear();
        for(int i=0; i < 12; i++){
            pits.add(new Pit());
        }
    }

    public void setUpStores(){
       // mancala.clear();
        mancala.add(new Store());
        mancala.add(new Store());
    }
    
    @Override
    public String toString(){
        StringBuilder boardRepresentation = new StringBuilder();
       
        boardRepresentation.append("\t\t\t\t-------------- OPPONENT'S SIDE ---------------- \n");
        boardRepresentation.append("\t\t\t\t12      11      10      9      8      7\n");
        boardRepresentation.append("\t\t\t\t");
        for(int i=11; i >=6; i--){
            boardRepresentation.append(pits.get(i).getStoneCount()+" |\t");
        }
        boardRepresentation.append("\n\t\t\t\t----------------------------------------------\n");
        boardRepresentation.append("\tOpponent's Store: ");
        
        boardRepresentation.append(mancala.get(1).getTotalStones()+ "\t\t\t\t\t\t\t\t");
        boardRepresentation.append("Player's Store: "+ mancala.get(0).getTotalStones()+"\n");
        boardRepresentation.append("\n\t\t\t\t----------------------------------------------\n");
        boardRepresentation.append("\t\t\t\t");
        for(int i=0; i <=5; i++){
            boardRepresentation.append(pits.get(i).getStoneCount()+" |\t");
        }
        boardRepresentation.append("\n\t\t\t\t1       2       3       4       5       6\n");
        boardRepresentation.append("\t\t\t\t-------------- PLAYER'S SIDE ---------------- \n");
        return boardRepresentation.toString();
    }
}
