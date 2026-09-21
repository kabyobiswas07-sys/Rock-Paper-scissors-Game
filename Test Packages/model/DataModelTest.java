package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class DataModelTest {

    private DataModel model;

    @Before
    public void setUp() {
        model = new DataModel();
    }

   

    @Test
    public void testInitialPlayerChoiceIsEmpty() {
        assertEquals("Player choice should start empty", "", model.getPlayerChoice());
    }

    @Test
    public void testInitialComputerChoiceIsEmpty() {
        assertEquals("Computer choice should start empty", "", model.getComputerChoice());
    }

    @Test
    public void testInitialResultIsEmpty() {
        assertEquals("Result should start empty", "", model.getResult());
    }

    @Test
    public void testInitialWinCountIsZero() {
        assertEquals(0, model.getWinCount());
    }

    @Test
    public void testInitialLoseCountIsZero() {
        assertEquals(0, model.getLoseCount());
    }

    @Test
    public void testInitialDrawCountIsZero() {
        assertEquals(0, model.getDrawCount());
    }

    @Test
    public void testInitialTotalRoundsIsZero() {
        assertEquals(0, model.getTotalRounds());
    }

 

    @Test
    public void testConstantRock()     { assertEquals("Rock",     DataModel.ROCK); }

    @Test
    public void testConstantPaper()    { assertEquals("Paper",    DataModel.PAPER); }

    @Test
    public void testConstantScissors() { assertEquals("Scissors", DataModel.SCISSORS); }

    @Test
    public void testConstantWin()      { assertEquals("WIN",  DataModel.WIN); }

    @Test
    public void testConstantLose()     { assertEquals("LOSE", DataModel.LOSE); }

    @Test
    public void testConstantDraw()     { assertEquals("DRAW", DataModel.DRAW); }

 

    @Test
    public void testSetAndGetPlayerChoice() {
        model.setPlayerChoice(DataModel.ROCK);
        assertEquals("Rock", model.getPlayerChoice());
    }

    @Test
    public void testSetAndGetComputerChoice() {
        model.setComputerChoice(DataModel.PAPER);
        assertEquals("Paper", model.getComputerChoice());
    }

    @Test
    public void testSetAndGetResult() {
        model.setResult(DataModel.WIN);
        assertEquals("WIN", model.getResult());
    }

 

    @Test
    public void testIncrementWin() {
        model.incrementWin();
        assertEquals(1, model.getWinCount());
    }

    @Test
    public void testIncrementLose() {
        model.incrementLose();
        assertEquals(1, model.getLoseCount());
    }

    @Test
    public void testIncrementDraw() {
        model.incrementDraw();
        assertEquals(1, model.getDrawCount());
    }

    @Test
    public void testMultipleIncrements() {
        model.incrementWin();
        model.incrementWin();
        model.incrementWin();
        model.incrementLose();
        model.incrementDraw();
        model.incrementDraw();
        assertEquals(3, model.getWinCount());
        assertEquals(1, model.getLoseCount());
        assertEquals(2, model.getDrawCount());
    }

    @Test
    public void testTotalRoundsCountsAllResults() {
        model.incrementWin();
        model.incrementWin();
        model.incrementLose();
        model.incrementDraw();
        assertEquals(4, model.getTotalRounds());
    }

   
    @Test
    public void testResetRoundClearsPlayerChoice() {
        model.setPlayerChoice(DataModel.ROCK);
        model.resetRound();
        assertEquals("", model.getPlayerChoice());
    }

    @Test
    public void testResetRoundClearsComputerChoice() {
        model.setComputerChoice(DataModel.PAPER);
        model.resetRound();
        assertEquals("", model.getComputerChoice());
    }

    @Test
    public void testResetRoundClearsResult() {
        model.setResult(DataModel.WIN);
        model.resetRound();
        assertEquals("", model.getResult());
    }

    @Test
    public void testResetRoundDoesNotClearScores() {
        model.incrementWin();
        model.incrementWin();
        model.incrementLose();
        model.resetRound();
        assertEquals("Wins should be preserved after resetRound", 2, model.getWinCount());
        assertEquals("Losses should be preserved after resetRound", 1, model.getLoseCount());
    }

   

    @Test
    public void testResetAllClearsEverything() {
        model.setPlayerChoice(DataModel.ROCK);
        model.setComputerChoice(DataModel.PAPER);
        model.setResult(DataModel.LOSE);
        model.incrementWin();
        model.incrementWin();
        model.incrementLose();
        model.incrementDraw();

        model.resetAll();

        assertEquals("", model.getPlayerChoice());
        assertEquals("", model.getComputerChoice());
        assertEquals("", model.getResult());
        assertEquals(0,  model.getWinCount());
        assertEquals(0,  model.getLoseCount());
        assertEquals(0,  model.getDrawCount());
        assertEquals(0,  model.getTotalRounds());
    }
}