package utils;

import model.DataModel;


public class Validator {

    
    public static boolean isValidChoice(String input) {
        return DataModel.ROCK.equals(input)
            || DataModel.PAPER.equals(input)
            || DataModel.SCISSORS.equals(input);
    }

    
    public static String getErrorMessage(String input) {
        if (input == null || input.isEmpty()) {
            return "No choice made yet. Please click Rock, Paper, or Scissors.";
        }
        return "\"" + input + "\" is not a valid choice.";
    }
}
