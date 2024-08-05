package mancala;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp(){
        board = new Board();
    }

    @Test
    public void testGetNumStones() throws PitNotFoundException{
        board.initializeBoard();
        int pitNum = 1;
        int stonesExpected = 4;
        int stonesAtPitNum = board.getNumStones(pitNum);
        assertEquals(stonesExpected, stonesAtPitNum);
    }

    @Test
    public void testWithInvalidPitPositionZero() throws PitNotFoundException{
        int pitNum = 0;
        assertThrows(PitNotFoundException.class, () -> board.getNumStones(pitNum));
    }

    @Test
    public void testWithInvalidPitPositionThirteen() throws PitNotFoundException{
        int pitNum = 13;
        assertThrows(PitNotFoundException.class, () -> board.getNumStones(pitNum));
    }

    @Test
    public void testIsSideEmpty() throws PitNotFoundException{
        for(int pitNum = 1; pitNum <=6; pitNum++){
            board.getNumStones(pitNum);
        }
        int pitNum = 1;
        boolean isEmpty = board.isSideEmpty(pitNum);
        assertTrue(isEmpty);
        
    }

    @Test
    public void testIsSideNotEmpty() throws PitNotFoundException{
        //checking that side is not empty for Player after board is initialized
        board.initializeBoard();
        for(int pitNum = 1; pitNum <=6; pitNum++){
            board.getNumStones(pitNum);
        }
        int pitNum = 1;
        boolean isEmpty = board.isSideEmpty(pitNum);
        assertFalse(isEmpty);
    }

    @Test
    public void testIsSideNotEmptyForOpponent() throws PitNotFoundException{
        //checking that side is not empty for Opponent after board is initialized
        board.initializeBoard();
        for(int pitNum = 7; pitNum <=12; pitNum++){
            board.getNumStones(pitNum);
        }
        int pitNum = 7;
        boolean isEmpty = board.isSideEmpty(pitNum);
        assertFalse(isEmpty);
    }

    @Test
    public void tesetResetBoard(){
        int initialStones = 0;
        for(int i=0; i < 12; i++){
            initialStones = board.getPits().get(i).getStoneCount();
            assertEquals(0, initialStones);
        }
        board.resetBoard();
        int stones = 0;
        int mancalaStones = 0;
        for(int i=0; i < 12; i++){
            stones = board.getPits().get(i).getStoneCount();
            assertEquals(4,stones);
        }
        for(int i=0; i < 2;i++){
            mancalaStones = board.getStores().get(i).getTotalStones();
            assertEquals(0, mancalaStones);
        }
    }

    @Test
    public void testRegisterPlayers(){
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");
        board.registerPlayers(playerOne, playerTwo);
        assertEquals(playerOne, board.getStores().get(0).getOwner());
        assertEquals(playerTwo, board.getStores().get(1).getOwner());

        assertEquals(board.getStores().get(0), playerOne.getStore());
        assertEquals(board.getStores().get(1), playerTwo.getStore());

        assertNotNull(playerOne.getStore());
        assertNotNull(playerTwo.getStore());
    }

    @Test
    public void testCaptureStones() throws PitNotFoundException {
        board.initializeBoard();
        Player playerOne = new Player("Player One");
        Player currentPlayer = playerOne;
        int stoppingPoint = 2;
        board.setCurrentPlayer(currentPlayer);
        int capturedStones = board.captureStones(stoppingPoint-1);
        assertTrue(capturedStones >=0, "Stones were captured");
    }
   
    @Test
    public void testDistributeStonesForEmptyBoard() throws PitNotFoundException {
        int startingPoint = 1;
        int stonesDistributed = board.distributeStones(1);
        assertEquals(0, stonesDistributed);
        assertFalse(stonesDistributed > 0, "no stones were distributed");
        int stonesAtNextIndex = board.getPits().get(0).getStoneCount();
        assertEquals(0,stonesAtNextIndex);
    }

    @Test
    public void testDistributeStonesInitializedBoard() throws PitNotFoundException {
        board.initializeBoard();
        int startingPoint = 1;
        int stonesDistributed = board.distributeStones(1);
        assertTrue(stonesDistributed>=0, "stones were distributed");
        for(int i=1; i <= 4; i++){
            int stonesCheck = board.getPits().get(i).getStoneCount();
            assertEquals(5, stonesCheck);
        }
    }

    @Test
    public void testDistributeStonesReachedStore() throws PitNotFoundException {
        board.initializeBoard();
        Player playerOne = new Player("Player One");
        Player currentPlayer = playerOne;
        board.setCurrentPlayer(currentPlayer);
        int startingPoint = 4;
        int stonesDistributed = board.distributeStones(startingPoint);
        assertTrue(stonesDistributed >0, "stones were distributed");
        int stonesAtNextPit = board.getPits().get(4).getStoneCount();
        assertEquals(5, stonesAtNextPit);
        int stonesAtStore = board.getCurrentPlayer().getStore().getTotalStones();
        assertEquals(1, stonesAtStore);
        int stonesAtStartingpoint = board.getPits().get(3).getStoneCount();
        assertEquals(0, stonesAtStartingpoint);

    }

    @Test
    public void distributeStonesOppositeSide() throws PitNotFoundException {
        board.initializeBoard();
        int startingPoint = 8;
        int stonesDistributed = board.distributeStones(startingPoint);
        assertTrue(stonesDistributed >=0, "stones were distributed");

        int stonesAtStartingpoint = board.getPits().get(7).getStoneCount();
        assertEquals(0, stonesAtStartingpoint);
        int stonesAtNextPit = board.getPits().get(8).getStoneCount();
        assertEquals(5, stonesAtNextPit);
    }

    @Test
    public void testDistributeStonesInvalidPit() throws PitNotFoundException {
        int startingPoint = 0;
        assertThrows(PitNotFoundException.class, () -> board.distributeStones(startingPoint));

        int startingPoint2 = 13;
        assertThrows(PitNotFoundException.class, () -> board.distributeStones(startingPoint2));

    }
}

