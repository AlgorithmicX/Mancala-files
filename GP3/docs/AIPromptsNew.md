# PROMPTS
I want to write a mancala game in OOP  using java so starting with the Pit class 
can you write a class that manages the pits in the board

the pit class should have only have methods called addStone, removeStones, getStoneCount
and a toString

write a store class for my mancala game with a constructor to initialize a new store and methods 
called addStones(int amount), emptyStore(), getOwner(), getTotalStones(), setOwner(Player player)
(There is a Player class) and a toString method

write a player class for me as a part of my mancala game that manages players and the methods in 
the class should be getName, getStore, getStoreCount,setName,setStore and a toString. It should 
contain a no parameter constructor that Initializes a new player and a constructor called Player(String name)
Initializes a new player with a name.

write a board class for me with a constructor that initializes a new Mancala board with pits and stores and methods called 
1. captureStones(int stoppingPoint) (Captures stones from the opponent's pits.), 2. distributeStones(int startingPoint)
(Helper method that distributes stones into pits and stores, skipping the opponent's store.)  3. getNumStones (int pitNum) 
(Gets the number of stones in a specific pit.), 4. getPits() (Returns the list of pits in the board, not including the stores) 5. getStores() 
(Returns the list of stores in the board, not including the pits) 6. a toString

the captureStones method captures stones from the opponent pits if the player lands at an empty pit 

the distribute stones method just distributes stones into the player's pits and stores but skips the opponent's stores

the distributeStones method returns the total number of stones added to pits and stores while the captureStones method returns The
number of stones captured, if any

okay. add these methods to my board class, 1. initializeBoard() (a void method that initializes the board by placing 4 stones each in the pits)
 2. isSideEmpty(int pitNum) (a boolean method that Indicates whether one side of the board is empty) 3. public int moveStones(int startPit,
Player player) throws InvalidMoveException Parameters:
startPit - The starting pit
player - The player making the move
Returns:
The total number of stones added to the corresponding store
Throws:
InvalidMoveException - If the move is invalid

the methods in the pit class are addStone(), getStoneCount(), and removeStones(), there is no setStones method

rewrite initialize board

add these methods to the board class: 1. void registerPlayers((Player one, Player two)
Connects Players to their Stores. 2. void resetBoard() (Resets the board by redistributing stones but retains the players.) 3. void setUpPits() 
(Establishes 12 empty Pits in the board) 4.void setUpStores()(Establishes 2 empty Stores in the board)

for the toString method, just display the state of the board with the 12 pits and two stores 

write a mancalaGame class with these methods: 1. a constructor called MancalaGame()
Initializes a new Mancala game. 2. public Board getBoard()
Gets the board for the game.
Returns:
the Board for the game 3. public void setBoard(Board theBoard)
Sets the board for the game.
Parameters:
theBoard - Board 4. public void setCurrentPlayer(Player player)
sets the current player 5. public Player getCurrentPlayer()
Gets the current player.
Returns:
The current player 6. a toString method

add these methods to the class 1. public void setPlayers(Player onePlayer,
 Player twoPlayer)
Sets the players for the game.
Parameters:
onePlayer - Player one
twoPlayer - Player two 2. public ArrayList<Player> getPlayers()
Gets the players for the game.
Returns:
An ArrayList of the players in the game 3. public int getNumStones(int pitNum)
                 throws PitNotFoundException
Gets the number of stones in a specific pit.
Parameters:
pitNum - The pit number
Returns:
The number of stones in the pit
Throws:
PitNotFoundException - If the pit number is invalid. 4. public int getStoreCount(Player player)
                  throws NoSuchPlayerException
Gets the total number of stones in a player's store.
Parameters:
player - The player
Returns:
The total number of stones in the player's store
Throws:
NoSuchPlayerException - If the player is not found 5. public Player getWinner()
throws GameNotOverException
Gets the winner of the game.
Returns:
The winning player or null for a tie
Throws:
GameNotOverException - If the game is not over yet

add these methods to the class: 1. public int move(int startPit)
         throws InvalidMoveException
Makes a move for the current player.
Parameters:
startPit - The pit from which to start the move
Returns:
the total number of stones remaining in the players side pits
Throws:
InvalidMoveException - If the move is invalid 2. public boolean isGameOver()
Checks if the game is over.
Returns:
True if the game is over, false otherwise 3. public void startNewGame()
Starts a new game by resetting the board.

write a no-parameter constructor for the board class 

can this be written better

can you write a GameNotOverException class for me
can you write an InvalidMoveException class for me
can you write a PitNotFoundException class for me
can you write a NoSuchPlayerException class for me

there is no setStore in the Pit class 
there is no setStones method

as regard this: there is no getStones() method: private int distributeStones(int startingPoint) {
    int stonesToDistribute = pits.get(startingPoint).getStones();
    pits.get(startingPoint).removeStones(); // Empty the starting pit

there is no getStore method for a pit 

rewrite the capture stones

there is no setStore method for a pit: for (int i = 0; i < pits.size(); i++) {
            if (i % (playerPitsCount + 1) != playerPitsCount) {
                pits.get(i).setStore(stores.get(i / (playerPitsCount + 1)));
            }
        }
    
there is no Setstore method!!
I dont have an associate with store method
there is no setStones method

write a textUI class that will play the game for me