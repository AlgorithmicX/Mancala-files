import java.util.*;

public class Board {
    private List<Pit> pits;
    private List<Store> stores;
    private int playerPitsCount;
    private Map<Pit, Store> pitToStoreMap; // Map to associate Pits with Stores

    public Board() {
        this(6, 0); // Default constructor with 6 pits per player and no initial stone placement
    }


    public Board(int playerPitsCount, int initialStoneCount) {
        this.playerPitsCount = playerPitsCount;
        this.pits = new ArrayList<>();
        this.stores = new ArrayList<>();
        initializeBoard();
    }

    private void initializeBoard() {
        int initialStoneCount = 4; // Set the initial stone count
    
        for (int i = 0; i < 2 * playerPitsCount; i++) {
            Pit pit = new Pit(initialStoneCount);
            pits.add(pit);
    
            if (i % (playerPitsCount + 1) == 0) {
                Store store = new Store();
                stores.add(store);
    
                // You may use a data structure to associate pits with stores, e.g., a Map
                // pitToStoreMap.put(pit, store);
            }
        }
    }
    
    
    
    
    private int distributeStones(int startingPoint) {
        int stonesToDistribute = pits.get(startingPoint).getStoneCount();
        pits.get(startingPoint).removeStones(); // Empty the starting pit
    
        int currentIndex = startingPoint + 1;
        int totalStonesDistributed = 0;
    
        while (stonesToDistribute > 0) {
            // Skip the opponent's stores
            if (currentIndex == playerPitsCount) {
                currentIndex++; // Skip the opponent's store
            }
    
            // If the currentIndex exceeds the total number of pits, wrap around to the beginning
            if (currentIndex == pits.size()) {
                currentIndex = 0;
            }
    
            // Distribute one stone to the current pit
            pits.get(currentIndex).addStone();
            stonesToDistribute--;
            totalStonesDistributed++;
    
            currentIndex++;
        }
    
        return totalStonesDistributed;
    }
    
    
    public int captureStones(int stoppingPoint) {
        Pit currentPit = pits.get(stoppingPoint);
        int stoneCount = currentPit.getStoneCount();
        int stonesCaptured = 0;

        if (stoneCount == 1) {
            // The last stone is placed in an empty pit; capture opponent's stones
            int oppositePitIndex = pits.size() - 2 - stoppingPoint; // Find the opposite pit
            Pit oppositePit = pits.get(oppositePitIndex);
            int capturedStones = oppositePit.getStoneCount();

            // Add captured stones to the current player's store
            Store currentPlayerStore = pitToStoreMap.get(currentPit);
            currentPlayerStore.addStones(capturedStones);
            stonesCaptured = capturedStones;

            // Empty both the current pit and the opponent's pit
            currentPit.removeStones();
            oppositePit.removeStones();
        }

        return stonesCaptured;
    }
    

    public int getNumStones(int pitNum) {
        if (pitNum >= 0 && pitNum < pits.size()) {
            return pits.get(pitNum).getStoneCount();
        }
        return 0; // Return 0 if the pit number is invalid
    }

    public List<Pit> getPits() {
        return pits;
    }

    public List<Store> getStores() {
        return stores;
    }

    
    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        if (startPit < 0 || startPit >= pits.size()) {
            throw new InvalidMoveException("Invalid starting pit");
        }
    
        Pit startPitObject = pits.get(startPit);
        int totalStonesAdded = 0;
    
        if (player == null) {
            throw new InvalidMoveException("Invalid player");
        }
    
        // Check if the player object matches the owner of the starting pit
        if (!player.equals(startPitObject.getOwner())) {
            throw new InvalidMoveException("Invalid player");
        }
    
        if (startPitObject.getStoneCount() == 0) {
            throw new InvalidMoveException("Starting pit is empty");
        }
    
        // Distribute the stones from the starting pit
        totalStonesAdded = distributeStones(startPit);
    
        return totalStonesAdded;
    }
    
    

    public boolean isSideEmpty(int pitNum) {
        boolean sideEmpty = true;
        int start = pitNum < playerPitsCount ? 0 : playerPitsCount + 1;
        int end = pitNum < playerPitsCount ? playerPitsCount : pits.size() - 1;

        for (int i = start; i <= end; i++) {
            if (pits.get(i).getStoneCount() > 0) {
                sideEmpty = false;
                break;
            }
        }

        return sideEmpty;
    }

    public void registerPlayers(Player one, Player two) {
        if (one != null && two != null) {
            stores.get(0).setOwner(one); // Set player one's store
            stores.get(1).setOwner(two); // Set player two's store
        }
    }

    // Resets the board by redistributing stones but retains the players
    // Resets the board by redistributing stones but retains the players
// Resets the board by redistributing stones but retains the players
public void resetBoard() {
    for (Pit pit : pits) {
        pit.removeStones(); // Empty all pits
    }
    initializeBoard(); // Redistribute stones to initialize the board
}



    // Establishes 12 empty Pits in the board
    public void setUpPits() {
        pits.clear();
        for (int i = 0; i < 12; i++) {
            pits.add(new Pit());
        }
    }
    public void setUpStores() {
        stores.clear();
        for (int i = 0; i < 2; i++) {
            stores.add(new Store());
        }
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
    
        boardString.append("Player Two's Store: ");
        boardString.append(stores.get(1).getTotalStones()); // Player Two's store
        boardString.append("\n");
    
        for (int i = 11; i >= 6; i--) {
            boardString.append("[").append(pits.get(i).getStoneCount()).append("] "); // Pits for Player Two
        }
        boardString.append("\n");
    
        boardString.append("Player One's Store: ");
        boardString.append(stores.get(0).getTotalStones()); // Player One's store
        boardString.append("\n");
    
        for (int i = 0; i < 6; i++) {
            boardString.append("[").append(pits.get(i).getStoneCount()).append("] "); // Pits for Player One
        }
        boardString.append("\n");
    
        return boardString.toString();
    }
}
