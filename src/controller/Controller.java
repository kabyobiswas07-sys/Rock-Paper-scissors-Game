package controller;

import model.DataModel;
import utils.Validator;


public class Controller {

    private DataModel model;

    public Controller() {
        model = new DataModel();
    }

   
    public String handlePlayerChoice(String choice) {

       
        if (!Validator.isValidChoice(choice)) {
            return Validator.getErrorMessage(choice);
        }

        
        model.setPlayerChoice(choice);

        
        return "You chose: " + model.getPlayerChoice()
             + " — waiting for computer... (coming in Week 5)";
    }

    public String handleReset() {
        model.reset();
        return "Choose Rock, Paper, or Scissors!";
    }

   
    public String getPlayerChoice() {
        return model.getPlayerChoice();
    }
}
