package model;


public class DataModel {

   
    public static final String ROCK     = "Rock";
    public static final String PAPER    = "Paper";
    public static final String SCISSORS = "Scissors";

    
    public static final String WIN  = "WIN";     // ← NEW Week 6
    public static final String LOSE = "LOSE";    // ← NEW Week 6
    public static final String DRAW = "DRAW";    // ← NEW Week 6

   
    private String playerChoice;
    private String computerChoice;
    private String result;                       

    public DataModel() {
        playerChoice   = "";
        computerChoice = "";
        result         = "";
    }

   
    public void setPlayerChoice(String choice)   { this.playerChoice = choice; }
    public String getPlayerChoice()              { return playerChoice; }

  
    public void setComputerChoice(String choice) { this.computerChoice = choice; }
    public String getComputerChoice()            { return computerChoice; }

   
    public void setResult(String result)         { this.result = result; }   
    public String getResult()                    { return result; }          

  
    public void reset() {
        playerChoice   = "";
        computerChoice = "";
        result         = "";   
    }
}
