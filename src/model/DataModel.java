package model;


public class DataModel {

    
    public static final String ROCK     = "Rock";
    public static final String PAPER    = "Paper";
    public static final String SCISSORS = "Scissors";

   
    private String playerChoice;

    public DataModel() {
        playerChoice = "";   
    }

   
    public void setPlayerChoice(String choice) {
        this.playerChoice = choice;
    }

    
    public String getPlayerChoice() {
        return playerChoice;
    }

    
    public void reset() {
        playerChoice = "";
    }
}
