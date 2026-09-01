package model;


public class DataModel {

    
    public static final String ROCK     = "Rock";
    public static final String PAPER    = "Paper";
    public static final String SCISSORS = "Scissors";

    private String playerChoice;
    private String computerChoice;   // ← NEW this week

    public DataModel() {
        playerChoice   = "";
        computerChoice = "";
    }

   
    public void setPlayerChoice(String choice) {
        this.playerChoice = choice;
    }

    public String getPlayerChoice() {
        return playerChoice;
    }

   
    public void setComputerChoice(String choice) {          // ← NEW
        this.computerChoice = choice;
    }

    public String getComputerChoice() {                     // ← NEW
        return computerChoice;
    }

   
    public void reset() {
        playerChoice   = "";
        computerChoice = "";   // ← also cleared now
    }
}
