package ui;

import java.util.Scanner;
import java.util.ArrayList;

import mancala.Board;
import mancala.GameNotOverException;
import mancala.InvalidMoveException;
import mancala.Player;
import mancala.MancalaGame;
import mancala.PitNotFoundException;

public class TextUI{
    private MancalaGame game;
    private Scanner scanner;
    private Player player;
    private Player playerOne;
    private Player playerTwo;
    private Board theBoard;

    public TextUI(){
        //playerOne = new Player("PlayerOne");
        //playerTwo = new Player("PlayerTwo");
        //game = new MancalaGame(playerOne, playerTwo);
        game = new MancalaGame();
        scanner = new Scanner(System.in);
        //theBoard = new Board();
        theBoard = game.getBoard();
        game.setBoard(theBoard);
        
        //theBoard.initializeBoard();
        ArrayList<Player> players = game.getPlayers();
         playerOne = players.get(0);
         playerTwo = players.get(1);
    }

    public void startGame(){
        printWelcomeMessage();
        int pitPosition;
        String input;
        displayBoard();
        game.setCurrentPlayer(playerOne);
        while(!game.isGameOver()){
            boolean checkIfMoveIsValid = false;
            while(!checkIfMoveIsValid){
                input = getInput();
                pitPosition = Integer.parseInt(input);
                try{
                    if ((game.getCurrentPlayer().getName().equals("PlayerOne") && pitPosition >= 1 && pitPosition<= 6)
                    || (game.getCurrentPlayer().getName().equals("PlayerTwo") && pitPosition >= 7 && pitPosition<= 12)){
                            checkIfMoveIsValid = true;
                    }else{
                            System.out.println("Pit is not valid. Please enter a pit on your side of the board");
                        }
                }catch(NumberFormatException e){
                    System.out.println("Input is invalid.");
                }
                if(checkIfMoveIsValid){
                    if(moveTry(pitPosition)){ //switchPlayers();
                        displayBoard();
                        if(!theBoard.hasExtraTurn()){
                            switchPlayers();
                        }else{
                            System.out.println(game.getCurrentPlayer().getName() + " gets an extra turn!");
                        }
                    }
                }
            }
        }
        scanner.close();
       
        if(game.isGameOver()){
            displayBoard();
            try{
                player = game.getWinner();
                if(player.getName().equals("PlayerTwo")){
                    System.out.println("The opponent WINS !!");
                }else{
                    System.out.println(player.getName()+" WINS !!");
                }
                
            }catch(GameNotOverException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayBoard(){
        System.out.println(game.toString());
    }
    
    private boolean moveTry(int pitPosition){
        try{
            int numCheck = game.getNumStones(pitPosition);
            if(numCheck == 0){
                System.out.println("there are no stones at that pit.please try again.");
                return false;
            }else{
                game.move(pitPosition);
                return true;
            }
        }catch(PitNotFoundException | InvalidMoveException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void switchPlayers(){
        if(game.getCurrentPlayer().getName().equals("PlayerOne")){
            game.setCurrentPlayer(playerTwo);
            System.out.println("its now " + game.getCurrentPlayer().getName() + " 's turn");
        }else if(game.getCurrentPlayer().getName().equals("PlayerTwo")){
            game.setCurrentPlayer(playerOne);
            System.out.println("its now " + game.getCurrentPlayer().getName() + " 's turn");
        }
    }

    private String getInput(){
        System.out.print("Enter a pit index or 'q' to quit: ");
        String input = scanner.next().toLowerCase();
        if(input.equals("q")){
            System.out.println("Game has been quit!");
            System.exit(0);
        }
        return input;
    }
    
    private void printWelcomeMessage(){
        System.out.println("\n------ Welcome to A Game of Mancala ! -------");
    }
   
    public static void main(String[]args){
        TextUI textUI = new TextUI();
        textUI.startGame();
    }
}
