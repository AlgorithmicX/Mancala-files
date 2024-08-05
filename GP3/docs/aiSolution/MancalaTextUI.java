import java.util.Scanner;

public class MancalaTextUI {
   
        public static void main(String[] args) {
            // Initialize the game
            MancalaGame game = new MancalaGame();
            Player player1 = new Player("Player 1");
            Player player2 = new Player("Player 2");
            game.setPlayers(player1, player2);
            
            // Set the initial currentPlayer
            game.setCurrentPlayer(player1);
        
            Scanner scanner = new Scanner(System.in);
            boolean gameOver = false;
        
            while (!gameOver) {
                displayBoard(game);
        
                Player currentPlayer = game.getCurrentPlayer();
                System.out.println(currentPlayer.getName() + "'s turn.");
                System.out.print("Enter the pit number to make a move (0-5): ");
                int pitNumber = scanner.nextInt();
        
                try {
                    int totalStonesRemaining = game.move(pitNumber);
                    System.out.println("Stones moved. " + totalStonesRemaining + " stones remaining.");
                } catch (InvalidMoveException e) {
                    System.out.println("Invalid move. Please try again.");
                }
        
                gameOver = game.isGameOver();
            }

        displayBoard(game);
        Player winner;
        try {
            winner = game.getWinner();
            System.out.println(winner.getName() + " wins the game!");
        } catch (GameNotOverException e) {
            System.out.println("It's a tie!");
        }

        scanner.close();
    }

    private static void displayBoard(MancalaGame game) {
        // Implement the code to display the game board (pits and stores) here.
        // You can use the toString method of the Board class to display the current state.
        System.out.println(game.getBoard().toString());
    }
}

