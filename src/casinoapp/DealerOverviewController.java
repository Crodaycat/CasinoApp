package casinoapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import casinoapp.MainApp;
import casinoapp.model.Dealer;
import casinoapp.model.Person;
import casinoapp.util.DateUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DealerOverviewController {
    @FXML
    private TableView<Dealer> personTable;
    @FXML
    private TableColumn<Dealer, String> firstNameColumn;
    @FXML
    private TableColumn<Dealer, String> lastNameColumn;
    @FXML
    private TableColumn<Dealer, String> idColumn;
    @FXML
    private Label idLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
   
    @FXML
    private Label dateLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public DealerOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
        @FXML
     private void initialize() {
         // Initialize the person table with the three columns.
         idColumn.setCellValueFactory(
                 cellData -> cellData.getValue().idProperty());
         firstNameColumn.setCellValueFactory(
                 cellData -> cellData.getValue().firstNameProperty());
         lastNameColumn.setCellValueFactory(
                 cellData -> cellData.getValue().lastNameProperty());
        

         // Clear person details.
         showDealerDetails(null);

         // Listen for selection changes and show the person details when changed.
         personTable.getSelectionModel().selectedItemProperty().addListener(
                 (observable, oldValue, newValue) -> showDealerDetails(newValue));
     }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getDealerData());
    }
    /**
 * Fills all text fields to show details about the person.
 * If the specified person is null, all text fields are cleared.
 * 
 * @param person the person or null
 */
    private void showDealerDetails(Person person) {
    if (person != null) {
        // Fill the labels with info from the person object.
        idLabel.setText(person.getId());
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        dateLabel.setText(DateUtil.format(person.getDate()));
    } else {
        // Person is null, remove all the text.
        idLabel.setText("");
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        dateLabel.setText("");
    }
   }
    
    @FXML
    private void handleDeleteDealer() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
    } else {
        // Nothing selected.
       Alert alert = new Alert(AlertType.WARNING);
       alert.setTitle("No Selection");
       alert.setHeaderText(null);
       alert.setContentText("Please select a person in the table.");
       alert.showAndWait();
    }
}
    /**
 * Called when the user clicks the new button. Opens a dialog to edit
 * details for a new person.
 */
@FXML
private void handleNewDealer() {
    Dealer tempDealer = new Dealer();
    boolean okClicked = mainApp.showDealerEditDialog(tempDealer);
    if (okClicked) {
        mainApp.getDealerData().add(tempDealer);
    }
}

/**
 * Called when the user clicks the edit button. Opens a dialog to edit
 * details for the selected person.
 */
@FXML
private void handleEditDealer() {
    Dealer selectedDealer = personTable.getSelectionModel().getSelectedItem();
    if (selectedDealer != null) {
        boolean okClicked = mainApp.showDealerEditDialog(selectedDealer);
        if (okClicked) {
            showDealerDetails(selectedDealer);
        }

    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}

}