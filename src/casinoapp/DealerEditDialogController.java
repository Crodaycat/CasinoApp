/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import casinoapp.model.Dealer;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import casinoapp.model.Person;
import casinoapp.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class DealerEditDialogController {
    @FXML
    private TextField idField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField baseMoneyField;
    @FXML
    private TextField workedHoursField;
    @FXML
    private TextField inMoneyBaccara;
    @FXML
    private TextField margingProfitPoker;
    @FXML
    private TextField margingProfitBaccara;
    @FXML
    private TextField margingProfitRulete;
    @FXML
    private TextField inMoneyBlackjack;
    @FXML
    private TextField inMoneyRulete;
    @FXML
    private TextField inMoneyPoker;
    @FXML
    private TextField margingProfitBlackjack;
        
    


    private Stage dialogStage;
    private Dealer dealer;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
        
        idField.setText(dealer.getId());
        firstNameField.setText(dealer.getFirstName());
        lastNameField.setText(dealer.getLastName());
//        dateField.setText(DateUtil.format(dealer.getDate()));
   //     dateField.setPromptText("dd.mm.yyyy");
        baseMoneyField.setText(String.valueOf(dealer.getBaseMoney()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            dealer.setFirstName(firstNameField.getText());
            dealer.setLastName(lastNameField.getText());
            dealer.setDate(DateUtil.parse(dateField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }

        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(dateField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}