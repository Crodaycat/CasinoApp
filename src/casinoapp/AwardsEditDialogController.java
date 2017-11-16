/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import casinoapp.model.Award;
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
public class AwardsEditDialogController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtPrice;
    
    
    private Stage dialogStage;
    private Award award;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setMachine(Award award) {
        this.award = award;
        
        txtId.setText(award.getAwardId());
        txtType.setText(award.getMachineType());
        txtDescription.setText(award.getDescription());
        txtPrice.setText(award.getPrice());
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            award.setAwardId(txtId.getText());
            award.setMachineType(txtType.getText());
            award.setDescription(txtDescription.getText());
            award.setPrice(txtPrice.getText());
            
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

        if (txtId.getText() == null || txtId.getText().length() == 0) {
            errorMessage += "No valid Id!\n"; 
        }
        if (txtType.getText() == null || txtType.getText().length() == 0) {
            errorMessage += "No valid Type!\n"; 
        }

        if (txtDescription.getText() == null || txtDescription.getText().length() == 0) {
            errorMessage += "No valid Description!\n";
        }
        
        if (txtPrice.getText() == null || txtPrice.getText().length() == 0) {
            errorMessage += "No valid Price!\n";
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
