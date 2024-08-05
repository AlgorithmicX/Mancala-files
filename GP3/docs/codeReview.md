# Project Title
CODE REVIEW OF THE AI GENERATED CODE FOR MANCALA GAME 

## Code Review
The AI generated code has short, meaningful and descriptive variable names across all its component classes, indicating 
its purpose. Variable names such as stones, owner (in Pit class), describe the number of stones in the pit and the owner 
of the Pit respectively. The variable names used such as totalStones, owner, pits, stores are used appropriately as seen
in the code. Every declaration of a variable declares a single variable. There is no declaration of multiple variables on a line.
Camel Case is used consistently where variable names are a fusion of multiple words. Method names are verbs
and contain meaningful descriptions. For instance, initializeBoard to initialize the board, getStoreCount to get the number
of stones in the player's store, addStone to add stones to the Pit. The method names consist of camel case where the the names
contain multiple words. Class names are very explicit and descriptive, written in UpperCamelCase.  This is seen in names such as Board, Pit, Store, 
Player and MancalaGame. Class names are very meaningful and are devoid of any ambiguity. Across the AI code, there is adherence to standard java naming conventions.

Braces are used consistently throughout the code with conditional statement and loops. There is only one statement on a line succeeded by a line break.
The code is neatly organized with appropriate use of indentation and whitespace thus enhancing readability.



## Functionality
The task of designing the mancala game is split amongst classes that work together to get the game running in alignment with OOP. The Board class models a Board
in the real world and is responsible for maintaining the Board. It contains methods that manipulate the state of the Board when required. The Pit, Store and Player
classes represent a pit in a real world mancala board, a store in a mancala board and a player who plays the game respectively. These classes manage the Pits, Stores
and Players in the game. The MancalaGame class also represents a game on the board and the MancalaTextUI class represents the interface where game play is visible.
The methods in each class manipulate the state of the object and since classes can communicate with one another, each class can call the methods to manipulate an 
objects outside its class through getters and setters.


## Correctness
The code runs however does not fulfil the expectations of the game. The board is being displayed alongside the stores with the correct number of stones
in each position however it is poorly done and its clustered. The game prompts Player one to enter a pit number from 0 to 5, however even when positions
ranging from 0 to 5 are entered it keeps telling the player that the move is invalid. This keeps the player stuck trying to find out what type of input is required
since the game prompted for 0 to 5 and when those values are entered it keeps saying they are invalid. Secondly, as a result of the problem previously mentioned
where the inputs ranging from 0 to 5 are treated as invalid even after prompting for that, it never switches to player Two. Player one would be stuck consistently trying
to make a move that player Two never gets a chance. Furthermore, there is no option to quit the game. This is not appropriate because a player should be allowed to take
control of the game and quit at will.


