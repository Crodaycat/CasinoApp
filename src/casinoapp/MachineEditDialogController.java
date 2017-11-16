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
    private TextField txtSerie;
    @FXML
    private TextField txtType;
    
    
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
        
        txtSerie.setText(machine.getSerie());
        txtType.setText(machine.getType());
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            machine.setSerie(txtSerie.getText());
            machine.setType(txtType.getText());
            
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

        if (txtSerie.getText() == null || txtSerie.getText().length() == 0) {
            errorMessage += "No valid Serie!\n"; 
        }
        
        if (txtType.getText() == null || txtType.getText().length() == 0) {
            errorMessage += "No valid Type!\n"; 
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
