import java.util.ArrayList;

public class MancalaGame {
    private Board board;
    private Player currentPlayer;
    private Player onePlayer; // Define onePlayer as an instance variable
    private Player twoPlayer; // Define twoPlayer as an instance variable

    public MancalaGame() {
        // Initialize a new Mancala game
        board = new Board(6, 4); // Assuming 6 pits per player and 4 stones per pit
        currentPlayer = null;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        this.onePlayer = onePlayer;
        this.twoPlayer = twoPlayer;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(onePlayer);
        players.add(twoPlayer);
        return players;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (board == null || pitNum < 0 || pitNum >= board.getPits().size()) {
            throw new PitNotFoundException("Invalid pit number");
        }
        return board.getNumStones(pitNum);
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException {
        if (player == null) {
            throw new NoSuchPlayerException("Player not found");
        }
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        int playerOneStoreCount = onePlayer.getStore().getTotalStones();
        int playerTwoStoreCount = twoPlayer.getStore().getTotalStones();

        if (playerOneStoreCount == 0 && playerTwoStoreCount == 0) {
            throw new GameNotOverException("The game is not over yet.");
        } else if (playerOneStoreCount > playerTwoStoreCount) {
            return onePlayer;
        } else if (playerTwoStoreCount > playerOneStoreCount) {
            return twoPlayer;
        } else {
            return null; // It's a tie
        }
    }

    public boolean isGameOver() {
        return board.isSideEmpty(0) || board.isSideEmpty(1);
    }
    

    public void startNewGame() {
        board.resetBoard();
        currentPlayer = onePlayer;
    }

    public int move(int startPit) throws InvalidMoveException {
        if (currentPlayer == null) {
            throw new InvalidMoveException("No current player set.");
        }
        
        // Check if it's the currentPlayer's turn
        if (!currentPlayer.equals(onePlayer) && !currentPlayer.equals(twoPlayer)) {
            throw new InvalidMoveException("Invalid current player.");
        }

        int totalStonesRemaining;
        try {
            totalStonesRemaining = board.moveStones(startPit, currentPlayer);
        } catch (InvalidMoveException e) {
            throw new InvalidMoveException(e.getMessage());
        }
        Player winner = null;
        // Check if the game is over after the move
        if (isGameOver()) {
            try{
                 winner = getWinner();
            }catch(GameNotOverException e){
                System.out.println("Game not over yet!");
            }
           
            if (winner != null) {
                System.out.println(winner.getName() + " wins the game!");
            } else {
                System.out.println("It's a tie!");
            }
        } else {
            // Switch the current player if the game is not over
            currentPlayer = (currentPlayer.equals(onePlayer)) ? twoPlayer : onePlayer;
        }

        return totalStonesRemaining;
    }

    @Override
    public String toString() {
        return "Mancala Game: \n" + board.toString() + "Current Player: " + (currentPlayer != null ? currentPlayer.getName() : "N/A");
    }
}

