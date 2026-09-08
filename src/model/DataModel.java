package model;

public class DataModel {

 
    public static final String ROCK     = "Rock";
    public static final String PAPER    = "Paper";
    public static final String SCISSORS = "Scissors";

   
    public static final String WIN  = "WIN";
    public static final String LOSE = "LOSE";
    public static final String DRAW = "DRAW";

   
    private String playerChoice;
    private String computerChoice;
    private String result;

  
    private int winCount;    //  Week 7
    private int loseCount;   
    private int drawCount;   

    public DataModel() {
        playerChoice   = "";
        computerChoice = "";
        result         = "";
        winCount       = 0;
        loseCount      = 0;
        drawCount      = 0;
    }

    
    public void setPlayerChoice(String choice)   { this.playerChoice = choice; }
    public String getPlayerChoice()              { return playerChoice; }

    
    public void setComputerChoice(String choice) { this.computerChoice = choice; }
    public String getComputerChoice()            { return computerChoice; }

   
    public void setResult(String result)         { this.result = result; }
    public String getResult()                    { return result; }

   
    public int getWinCount()                     { return winCount; }  
    public int getLoseCount()                    { return loseCount; }  
    public int getDrawCount()                    { return drawCount; }  

   
    public void incrementWin()                   { winCount++; }      
    public void incrementLose()                  { loseCount++; }     
    public void incrementDraw()                  { drawCount++; }      
   
    public int getTotalRounds() {                                       
        return winCount + loseCount + drawCount;
    }


    
    public void resetRound() {                                           
        playerChoice   = "";
        computerChoice = "";
        result         = "";
    }

   
    
    public void resetAll() {                                            
        resetRound();
        winCount  = 0;
        loseCount = 0;
        drawCount = 0;
    }
}
