package utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {


// valid choices 
    @Test
    public void testRockIsValid() {
        assertTrue("Rock should be a valid choice",
                   Validator.isValidChoice("Rock"));
    }

    @Test
    public void testPaperIsValid() {
        assertTrue("Paper should be a valid choice",
                   Validator.isValidChoice("Paper"));
    }

    @Test
    public void testScissorsIsValid() {
        assertTrue("Scissors should be a valid choice",
                   Validator.isValidChoice("Scissors"));
    }

  
// invalid inputs 
    @Test
    public void testEmptyStringIsInvalid() {
        assertFalse("Empty string should be invalid",
                    Validator.isValidChoice(""));
    }

    @Test
    public void testNullIsInvalid() {
        assertFalse("Null should be invalid",
                    Validator.isValidChoice(null));
    }

    @Test
    public void testRandomWordIsInvalid() {
        assertFalse("Random word should be invalid",
                    Validator.isValidChoice("Lizard"));
    }

    @Test
    public void testNumberIsInvalid() {
        assertFalse("Number string should be invalid",
                    Validator.isValidChoice("123"));
    }

    @Test
    public void testSpaceIsInvalid() {
        assertFalse("Whitespace should be invalid",
                    Validator.isValidChoice("   "));
    }

  
// case sensivity
    @Test
    public void testLowercaseRockIsInvalid() {
        assertFalse("Lowercase 'rock' should fail",
                    Validator.isValidChoice("rock"));
    }

    @Test
    public void testUppercasePaperIsInvalid() {
        assertFalse("All-caps 'PAPER' should fail",
                    Validator.isValidChoice("PAPER"));
    }

    @Test
    public void testMixedCaseScissorsIsInvalid() {
        assertFalse("Mixed case should fail",
                    Validator.isValidChoice("sCiSsOrS"));
    }


//error message 
    @Test
    public void testErrorMessageForNull() {
        String msg = Validator.getErrorMessage(null);
        assertNotNull("Error message should not be null", msg);
        assertFalse("Error message should not be empty", msg.isEmpty());
    }

    @Test
    public void testErrorMessageForEmptyString() {
        String msg = Validator.getErrorMessage("");
        assertNotNull(msg);
        assertFalse(msg.isEmpty());
    }

    @Test
    public void testErrorMessageContainsInvalidInput() {
        String msg = Validator.getErrorMessage("Banana");
        assertTrue("Error message should mention the bad input",
                   msg.contains("Banana"));
    }
}
