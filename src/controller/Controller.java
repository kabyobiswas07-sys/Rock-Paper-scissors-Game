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

       
        String computerChoice = generateComputerChoice();
        model.setComputerChoice(computerChoice);

           return "You chose: " + model.getPlayerChoice()
             + "   |   Computer chose: " + model.getComputerChoice();
    }

    
    private String generateComputerChoice() {
        int index = random.nextInt(CHOICES.length);   // 0, 1, or 2
        return CHOICES[index];
    }

  

    public String handleReset() {
        model.reset();
        return "Choose Rock, Paper, or Scissors!";
    }

   
    public String getPlayerChoice() {
        return model.getPlayerChoice();
    }

    public String getComputerChoice() {             // ← NEW
        return model.getComputerChoice();
    }
}
