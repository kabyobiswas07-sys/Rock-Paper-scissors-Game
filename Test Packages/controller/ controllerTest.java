package controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }


// choice of P
    @Test
    public void testHandleRockReturnsMessage() {
        String result = controller.handlePlayerChoice("Rock");
        assertNotNull("Should return a non-null message", result);
        assertFalse("Message should not be empty", result.isEmpty());
    }

    @Test
    public void testHandlePaperReturnsMessage() {
        String result = controller.handlePlayerChoice("Paper");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testHandleScissorsReturnsMessage() {
        String result = controller.handlePlayerChoice("Scissors");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testInvalidChoiceReturnsErrorMessage() {
        String result = controller.handlePlayerChoice("Banana");
        assertNotNull("Invalid choice should still return a message", result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testNullChoiceDoesNotCrash() {
        String result = controller.handlePlayerChoice(null);
        assertNotNull(result);
    }

 // choice of C
    @Test
    public void testComputerChoiceIsValidAfterRound() {
        controller.handlePlayerChoice("Rock");
        String comp = controller.getComputerChoice();
        assertTrue("Computer must pick Rock, Paper, or Scissors",
                   comp.equals("Rock") || comp.equals("Paper") || comp.equals("Scissors"));
    }

    @Test
    public void testComputerChoiceChangesOverTime() {
        String first = null;
        boolean foundDifferent = false;
        for (int i = 0; i < 20; i++) {
            controller.handlePlayerChoice("Rock");
            String comp = controller.getComputerChoice();
            if (first == null) {
                first = comp;
            } else if (!comp.equals(first)) {
                foundDifferent = true;
                break;
            }
        }
        assertTrue("Computer should not always pick the same choice", foundDifferent);
    }

//result 
    @Test
    public void testResultIsValidAfterRound() {
        controller.handlePlayerChoice("Rock");
        String result = controller.getResult();
        assertTrue("Result must be WIN, LOSE, or DRAW",
                   result.equals("WIN") || result.equals("LOSE") || result.equals("DRAW"));
    }


//score tracking 
    @Test
    public void testTotalRoundsIncrementAfterEachRound() {
        assertEquals(0, controller.getTotalRounds());
        controller.handlePlayerChoice("Rock");
        assertEquals(1, controller.getTotalRounds());
        controller.handlePlayerChoice("Paper");
        assertEquals(2, controller.getTotalRounds());
    }

    @Test
    public void testScoreCountsAddUpToTotalRounds() {
        for (int i = 0; i < 10; i++) {
            controller.handlePlayerChoice("Rock");
        }
        int total = controller.getWinCount()
                  + controller.getLoseCount()
                  + controller.getDrawCount();
        assertEquals("Wins + Losses + Draws must equal total rounds",
                     controller.getTotalRounds(), total);
    }

    @Test
    public void testInvalidChoiceDoesNotIncrementScore() {
        controller.handlePlayerChoice("Banana");
        assertEquals("Score should not change for invalid input",
                     0, controller.getTotalRounds());
    }


//reset message
    @Test
    public void testWinMessageContainsWin() {
        for (int i = 0; i < 50; i++) {
            String msg = controller.handlePlayerChoice("Rock");
            if (controller.getResult().equals("WIN")) {
                assertTrue(msg.contains("WIN") || msg.contains("🎉"));
                return;
            }
        }
        fail("Should have won at least once in 50 rounds");
    }

    @Test
    public void testLoseMessageContainsLose() {
        for (int i = 0; i < 50; i++) {
            String msg = controller.handlePlayerChoice("Rock");
            if (controller.getResult().equals("LOSE")) {
                assertTrue(msg.contains("LOSE") || msg.contains("😞"));
                return;
            }
        }
        fail("Should have lost at least once in 50 rounds");
    }

    @Test
    public void testDrawMessageContainsDraw() {
        for (int i = 0; i < 100; i++) {
            String msg = controller.handlePlayerChoice("Rock");
            if (controller.getResult().equals("DRAW")) {
                assertTrue(msg.contains("DRAW") || msg.contains("🤝"));
                return;
            }
        }
        fail("Should have drawn at least once in 100 rounds");
    }

   
//reset all
    @Test
    public void testHandleResetReturnsDefaultMessage() {
        String msg = controller.handleReset();
        assertEquals("Choose Rock, Paper, or Scissors!", msg);
    }

    @Test
    public void testHandleResetPreservesScores() {
        controller.handlePlayerChoice("Rock");
        controller.handlePlayerChoice("Rock");
        int totalBefore = controller.getTotalRounds();
        controller.handleReset();
        assertEquals("Scores should be preserved after handleReset()",
                     totalBefore, controller.getTotalRounds());
    }

  

    @Test
    public void testHandleResetAllReturnsDefaultMessage() {
        String msg = controller.handleResetAll();
        assertEquals("Choose Rock, Paper, or Scissors!", msg);
    }

    @Test
    public void testHandleResetAllClearsAllScores() {
        controller.handlePlayerChoice("Rock");
        controller.handlePlayerChoice("Rock");
        controller.handlePlayerChoice("Rock");
        controller.handleResetAll();
        assertEquals(0, controller.getWinCount());
        assertEquals(0, controller.getLoseCount());
        assertEquals(0, controller.getDrawCount());
        assertEquals(0, controller.getTotalRounds());
    }

    @Test
    public void testComputerChoiceIsEmptyAfterResetAll() {
        controller.handlePlayerChoice("Rock");
        controller.handleResetAll();
        assertEquals("", controller.getComputerChoice());
    }
}
