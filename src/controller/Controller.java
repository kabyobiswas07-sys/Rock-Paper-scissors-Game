package controller;

import model.DataModel;
import utils.Validator;
import java.util.Random;



public class Controller {

    private DataModel model;
    private Random    random;

    private static final String[] CHOICES = {
        DataModel.ROCK,
        DataModel.PAPER,
        DataModel.SCISSORS
    };

    public Controller() {
        model  = new DataModel();
        random = new Random();
    }

 
   
    public String handlePlayerChoice(String choice) {

       
        if (!Validator.isValidChoice(choice)) {
            return Validator.getErrorMessage(choice);
        }

      
        model.setPlayerChoice(choice);

     
        model.setComputerChoice(generateComputerChoice());

        
        String result = determineResult(
            model.getPlayerChoice(),
            model.getComputerChoice()
        );
        model.setResult(result);

        //  the score  ← NEW Week 7
        updateScore(result);

      
        return buildOutcomeMessage();
    }

  

 
    private void updateScore(String result) {
        switch (result) {
            case DataModel.WIN:  model.incrementWin();  break;
            case DataModel.LOSE: model.incrementLose(); break;
            default:             model.incrementDraw(); break;
        }
    }

   
    
    public String getScoreSummary() {                                  // ← NEW
        return "Wins: "   + model.getWinCount()
             + "   Losses: " + model.getLoseCount()
             + "   Draws: "  + model.getDrawCount()
             + "   |   Rounds: " + model.getTotalRounds();
    }


    public int getWinCount()   { return model.getWinCount(); }        // ← NEW
    public int getLoseCount()  { return model.getLoseCount(); }       // ← NEW
    public int getDrawCount()  { return model.getDrawCount(); }       // ← NEW
    public int getTotalRounds(){ return model.getTotalRounds(); }     // ← NEW


    public String handleReset() {
        model.resetRound();
        return "Choose Rock, Paper, or Scissors!";
    }

   
    public String handleResetAll() {                                   // ← NEW
        model.resetAll();
        return "Choose Rock, Paper, or Scissors!";
    }

 

    private String determineResult(String player, String computer) {
        if (player.equals(computer)) {
            return DataModel.DRAW;
        }
        if ((player.equals(DataModel.ROCK)     && computer.equals(DataModel.SCISSORS)) ||
            (player.equals(DataModel.SCISSORS) && computer.equals(DataModel.PAPER))    ||
            (player.equals(DataModel.PAPER)    && computer.equals(DataModel.ROCK))) {
            return DataModel.WIN;
        }
        return DataModel.LOSE;
    }

    private String buildOutcomeMessage() {
        String player   = model.getPlayerChoice();
        String computer = model.getComputerChoice();
        String result   = model.getResult();
        String reason   = getReasonMessage(player, computer, result);

        switch (result) {
            case DataModel.WIN:  return reason + " — You WIN! 🎉";
            case DataModel.LOSE: return reason + " — You LOSE! 😞";
            default:             return "Both chose " + player + " — It's a DRAW! 🤝";
        }
    }

    private String getReasonMessage(String player, String computer, String result) {
        if (result.equals(DataModel.DRAW)) return "";
        if ((player.equals(DataModel.ROCK)     && computer.equals(DataModel.SCISSORS)) ||
            (player.equals(DataModel.SCISSORS) && computer.equals(DataModel.ROCK)))
            return "Rock crushes Scissors";
        if ((player.equals(DataModel.SCISSORS) && computer.equals(DataModel.PAPER)) ||
            (player.equals(DataModel.PAPER)    && computer.equals(DataModel.SCISSORS)))
            return "Scissors cuts Paper";
        if ((player.equals(DataModel.PAPER)    && computer.equals(DataModel.ROCK)) ||
            (player.equals(DataModel.ROCK)     && computer.equals(DataModel.PAPER)))
            return "Paper covers Rock";
        return "";
    }

    //  Computer random move generator (unchanged from Week 5)

    private String generateComputerChoice() {
        return CHOICES[random.nextInt(CHOICES.length)];
    }

  

    public String getPlayerChoice()   { return model.getPlayerChoice(); }
    public String getComputerChoice() { return model.getComputerChoice(); }
    public String getResult()         { return model.getResult(); }
}
