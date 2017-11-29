/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import casinoapp.model.Machine;
import casinoapp.util.DateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class MachineEditDialogController {
    
    @FXML
    private TextField machineSerieField;
    @FXML
    private TextField machineAwardField;
    @FXML
    private TextField machineMoneyCollectedField;
    @FXML
    private TextField machineAwardDateField;
    @FXML
    private TextField machinePriceField;
    
    private Stage dialogStage;
    private Machine machine;
    private boolean okClicked = false;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        
    }    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setMachine(Machine machine) {
        this.machine = machine;
        
        machineSerieField.setText(machine.getSerie());
        machineAwardField.setText(String.valueOf(machine.getAward()));
        machineMoneyCollectedField.setText(String.valueOf(machine.getMoneyCollected()));
        machineAwardDateField.setText(DateUtil.format(machine.getAwardDate()));
        machineAwardDateField.setPromptText("dd.mm.yyyy");
        machinePriceField.setText(String.valueOf(machine.getPrice()));
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            machine.setSerie(machineSerieField.getText());
            machine.setAward(Float.parseFloat(machineAwardField.getText()));
            machine.setMoneyCollected(Float.parseFloat(machineMoneyCollectedField.getText()));
            machine.setAwardDate(DateUtil.parse(machineAwardDateField.getText()));
            machine.setPrice(Float.parseFloat(machinePriceField.getText()));
            
            
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (machineSerieField.getText() == null || machineSerieField.getText().length() == 0) {
            errorMessage += "No valid Serie!\n"; 
        }
        
        if (machineAwardDateField.getText() == null || machineAwardDateField.getText().length() == 0) {
            errorMessage += "No valid Date!\n"; 
        }
        if (Double.parseDouble(machinePriceField.getText()) > Double.parseDouble(machineMoneyCollectedField.getText())) {
            errorMessage += "No valid Profit!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
}
