package mancala;

import java.util.ArrayList;

public class MancalaGame {
    private Player playerOne;
    private Player playerTwo;
    private Board mancalaBoard;
    private Player currentPlayer;
    

    public MancalaGame(){
        mancalaBoard = new Board();
        mancalaBoard.initializeBoard();
        //setBoard(mancalaBoard);
        playerOne = new Player("PlayerOne");
        playerTwo = new Player("PlayerTwo");
        setPlayers(playerOne, playerTwo);
    }

    public MancalaGame(Player one, Player two){
        mancalaBoard = new Board();
        this.playerOne = one;
        this.playerTwo = two;
    }
  
    public Board getBoard(){
        return mancalaBoard;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    

    public int getNumStones(int pitNum) throws PitNotFoundException{
        
        if(pitNum < 1 || pitNum > 12){
            throw new PitNotFoundException("INVALID PIT NUMBER");
        }
        return mancalaBoard.getNumStones(pitNum);
    }

    public ArrayList<Player> getPlayers(){
        ArrayList<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(playerOne);
        listOfPlayers.add(playerTwo);
        return listOfPlayers;
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException{
        if(player == playerOne){
            return playerOne.getStore().getTotalStones();
        }else if(player == playerTwo){
            return playerTwo.getStore().getTotalStones();
        }else{
            throw new NoSuchPlayerException("Player does not exist...");
        }
    }

    public Player getWinner() throws GameNotOverException{
        //System.out.println("value of is game over is " + isGameOver());
        if(!isGameOver()){
            throw new GameNotOverException("Game is NOT OVER YET !");
        }

        int playerOneStoreCount = playerOne.getStore().emptyStore();
        int playerTwoStoreCount = playerTwo.getStore().emptyStore();

        if(playerOneStoreCount == playerTwoStoreCount){
            System.out.println("Its a TIE!");
            return null;
        }else if(playerOneStoreCount > playerTwoStoreCount){
            return playerOne;
        }else{
            return playerTwo;
        }
    }

    /*public boolean isGameOver(){
        try{
            return mancalaBoard.isSideEmpty(1) || mancalaBoard.isSideEmpty(7);
        }catch(PitNotFoundException e){
           System.out.println(e.getMessage());
           return false;
        }
    }*/
    public boolean isGameOver() {
        boolean gameOver = false;
        try {
            gameOver = mancalaBoard.isSideEmpty(1) || mancalaBoard.isSideEmpty(7);
        } catch (PitNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return gameOver;
    }
    


    public int move(int startPit) throws InvalidMoveException{
        currentPlayer = getCurrentPlayer();
        
        int total = 0;
       
        try{
            mancalaBoard.moveStones(startPit, currentPlayer);
            if(startPit >=1 && startPit <= 6){ //same as saying is currentplayer is player one
                for(int i=0; i < 6; i++){
                    total += mancalaBoard.getPits().get(i).getStoneCount();
                }
            }else if(startPit >=7 && startPit <= 12){
                for(int i=6; i < 12; i++){
                    total += mancalaBoard.getPits().get(i).getStoneCount();
                }
            }
            return total;
        }catch(InvalidMoveException e){
            throw e;
            //System.out.println(e.getMessage());  
        }
        //return -1;   
    }
    
    public void setBoard(Board theBoard){
        this.mancalaBoard = theBoard;
        this.mancalaBoard.registerPlayers(playerOne, playerTwo);
    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;

    }

    public void setPlayers(Player onePlayer, Player twoPlayer){
        this.playerOne = onePlayer;
        this.playerTwo = twoPlayer;
        setCurrentPlayer(onePlayer);
    }

    public void startNewGame(){
        mancalaBoard.resetBoard();
    }

    @Override
    public String toString(){
        return mancalaBoard.toString();
    }
}

        