# Project Title
IMPLEMENTATION OF MANCALA GAME

- This project contains a system that helps a user to play a mancala game. A GUI is incorporated
into this project for cleaner visibility during game play

## Description
- This program helps user play a mancala game. The board is displayed when the game is started and after each move, making the state of the game at each
point visible. The board is labelled 1 - 12 with pits 1-6 representing the player's side of the board and 7-12 representing the opponent's side of the board.
This program will help a user play a mancala game in accordance with the rules and when the game is over, the winner will be displayed. A player may enter q at 
any point in the game to quit or exit the game. The Pit, Store, Player, Board and MancalaGame classes are the foundation of the game and without them the 
game will not be successful driven in the TextUI class which provides an interface for the players to play the game and helps to make the game visible.


## Getting Started

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program: java.util.ArrayList


### Executing program

   `gradle build`
   `gradle echo`
   cut/paste the output of gradle echo to the terminal

* Step-by-step bullets
```
gradle build
gradle echo
java -cp build/classes/java/main ui.TextUI
```
* include the expected output

## WHY IMPLEMENTATION OF THE MANCALA GAME USING MY VERSION IS BETTER THAN THE AI VERSION
The AI version is written using OOP as it divides the task into a set of classes which provide means of manipulating their internal state and through
the process of invoking methods these classes work together to create the game. Despite this, it does not fulfil the expectations of the game. My version 
is better than the AI version because the classes that form the foundation of the game which are Board, Pit, Player, Store and MancalaGame are well written 
and facilitate the user in playing the game as opposed to the AI version. The Board class maintains the Board and provides methods that mainipulate the state
of the board when required. The Store class maintains the store. Similarly, the Player, Pit and MancalaGame classes, maintain the Player who plays a game, the 
Pits on the board and a mancala game in this implementation.
The state of the board is displayed at the start of the game, explicitly displaying each player's side of the board and their stores which the AI does not do.
My implementation also validates input to ensure that the player makes a move on their side of the board. It switches players seamlessly, captures stones when the
capture condition is met and displays the winners at the end of the game.

Modelling the game using OOP is a great approach because it makes it easy to modify and preserve the code while allowing creation of new objects that inherit the traits
of existing ones. It allows for a quicker and streamlined execution providing a coherent structure for the programs. OOP faciliates the expansion of the program to handle
increasing data volumes. OOP facilitates encapsulation and abstraction, allowing functionalities to be adjusted without impacting the entirety of the codebase. This lead to 
enhanced readability, and the capacity to extend the system as the demands increase. The program can be easily maintained. For instance, if any functionality needs to be added
to the board class, it can be smoothly integrated into the code while preserving the other classes.
The objects created are able to communicate with one another through member functions. The Board class communicates with the Pit, store and player classes. The TextUI class
which embodies the functionality of the game is founded upon every other class which works in unison to fuel the game.
It is secure and access can be controlled. In the Board class, by making the pits, stores, and players private their internal state is protected and modified when required
using the methods from their respective classes. My Implementation using OOP is a powerful approach as it divides the task
into manageable components. This approach enhances problem solving efficiency and contributes to an overall improvement in performance.



## Limitations

What isn't done? What things cause errors?   

## Author Information

Your name and contact information including your email address
- ESTHER OGECHUKWUKA NWACHUKWU enwachuk@uoguelph.ca

## SOURCES  
"Java - What is OOP ?", (https://www.w3schools.com/java/java_oop.asp)
"Benefits of OOPs in Java: A comprehensive Overview", (https://trainings.internshala.com/blog/benefits-of-oops-in-java/)
"Google Java Style Guide", (https://google.github.io/styleguide/javaguide.html#s5.1-identifier-names)

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)



# Mancala-files
