/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import casinoapp.model.Award;
import casinoapp.model.GameHistory;
import casinoapp.model.Machine;
import casinoapp.util.DateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class MachineOverviewController {

    @FXML
    private TableView<Machine> machinesTable;
    @FXML
    private TableColumn<Machine, String> machineSerie;
    @FXML
    private TableColumn<Machine, String> machineType;
    @FXML
    private Label machineSerieLabel;
    @FXML
    private Label machineTypeLabel;
    @FXML
    private TableView<Award> awardsTable;
    @FXML
    private TableColumn<Award, String> awardIdCol;
    @FXML
    private TableColumn<Award, String> awardMachineTypeCol;
    @FXML
    private TableColumn<Award, String> awardDescriptionCol;
    @FXML
    private TableColumn<Award, String> awardPriceCol;
    @FXML
    private TableView<GameHistory> gameHistoryTable;
    @FXML
    private Label awardId;
    @FXML
    private Label awardMachineType;
    @FXML
    private Label awardDescription;
    @FXML
    private Label awardPrice;
    @FXML
    private TableColumn<GameHistory, String> gameMachineSerie;
    @FXML
    private TableColumn<GameHistory, String> gameAwardId;
    @FXML
    private TableColumn<GameHistory, String> gameMoneyCollected;
    @FXML
    private TableColumn<GameHistory, LocalDate> gameAwardDate;
    @FXML
    private TableColumn<GameHistory, String> gameAwardPrice;
    @FXML
    private Label gameMachineLabel;
    @FXML
    private Label gameMoneyLabel;
    @FXML
    private Label gameAwardLabel;
    @FXML
    private Label gameDateLabel;
    @FXML
    private Label gamePriceLabel;
    
    private MainApp mainApp;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        initializeMachineTable();
        initializeAwardTable();
        initializeGameHistoryTable();
        
        try {
            machinesTable.setItems(mainApp.getMachineData());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        //awardsTable.setItems(mainApp.getAwardData());
        //gameHistoryTable.setItems(mainApp.getGameHistoryData());
    }    

    @FXML
    private void handleNewMachine(ActionEvent event) {
        Machine tempMachine = new Machine();
        boolean okClicked = mainApp.showMachineEditDialog(tempMachine);
        if (okClicked) {
            mainApp.getMachineData().add(tempMachine);
        }
        
    }

    @FXML
    private void handleEditMachine(ActionEvent event) {
        Machine selectedMachine = machinesTable.getSelectionModel().getSelectedItem();
        if (selectedMachine != null) {
            boolean okClicked = mainApp.showMachineEditDialog(selectedMachine);
            if (okClicked) {
                showMachineDatails(selectedMachine);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Machine Selected");
            alert.setContentText("Please select a Machine in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteMachine(ActionEvent event) {
        int selectedIndex = machinesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            machinesTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("No Selection");
           alert.setHeaderText(null);
           alert.setContentText("Please select a Machine in the table.");
           alert.showAndWait();
        }
    }

    @FXML
    private void handleExportPDF(ActionEvent event) {
    }

    @FXML
    private void handleNewAward() {
        Award tempAward = new Award();
        boolean okClicked = mainApp.showAwardEditDialog(tempAward);
        if (okClicked) {
            mainApp.getAwardData().add(tempAward);
        }
        
    }

    @FXML
    private void handleEditAward() {
        Award selectedAward = awardsTable.getSelectionModel().getSelectedItem();
        if (selectedAward != null) {
            boolean okClicked = mainApp.showAwardEditDialog(selectedAward);
            if (okClicked) {
                showAwardDatails(selectedAward);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Machine Selected");
            alert.setContentText("Please select a Machine in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteAward() {
        int selectedIndex = awardsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            awardsTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("No Selection");
           alert.setHeaderText(null);
           alert.setContentText("Please select a Award in the table.");
           alert.showAndWait();
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //machineType.setItems(mainApp.getMachineData());
    }
    
    private void showMachineDatails (Machine machine) {
        if (machine != null) {
            machineSerieLabel.setText(machine.getSerie());
            machineTypeLabel.setText(machine.getType());
        } else {
            machineSerieLabel.setText("");
            machineTypeLabel.setText("");
        }
    }
    
    private void showAwardDatails (Award award) {
        if (award != null) {
            
            awardId.setText(award.getAwardId());
            awardMachineType.setText(award.getMachineType());
            awardDescription.setText(award.getDescription());
            awardPrice.setText(String.valueOf(award.getPrice()));
        } else {
           awardId.setText("");
           awardMachineType.setText("");
           awardDescription.setText("");
           awardPrice.setText(""); 
        }
    }
    
    private void showGameHistoryDatails (GameHistory gameHistory) {
        if (gameHistory != null) {
            gameMachineLabel.setText(gameHistory.getMachineSerie());
            gameMoneyLabel.setText(String.valueOf(gameHistory.getMoneyCollected()));
            gameAwardLabel.setText(gameHistory.getAwardId());
            gameDateLabel.setText(DateUtil.format(gameHistory.getAwardDate()));
            gamePriceLabel.setText(gameHistory.getAwardPrice());
        } else {
            gameMachineLabel.setText("");
            gameMoneyLabel.setText("");
            gameAwardLabel.setText("");
            gameDateLabel.setText("");
            gamePriceLabel.setText("");
        }
    }
    
    private void initializeMachineTable () {
        machineSerie.setCellValueFactory(
            cellData -> cellData.getValue().getSerieProperty());
        machineType.setCellValueFactory(
            cellData -> cellData.getValue().getTypeProperty());
        
        showMachineDatails(null);
        
        machinesTable.getSelectionModel().selectedItemProperty().addListener(
                 (observable, oldValue, newValue) -> showMachineDatails(newValue));
    }
    
    private void initializeAwardTable () {
        awardIdCol.setCellValueFactory(
            cellData -> cellData.getValue().getAwardIdProperty());
        awardMachineTypeCol.setCellValueFactory(
            cellData -> cellData.getValue().getMachineTypeProperty());
        awardDescriptionCol.setCellValueFactory(
            cellData -> cellData.getValue().getDescriptionProperty());
        awardPriceCol.setCellValueFactory(
            cellData -> cellData.getValue().getPriceProperty());
        
        showAwardDatails(null);
        
        awardsTable.getSelectionModel().selectedItemProperty().addListener(
                 (observable, oldValue, newValue) -> showAwardDatails(newValue));
    }
    
    private void initializeGameHistoryTable () {
        gameMachineSerie.setCellValueFactory (
            cellData -> cellData.getValue().getMachineSerieProperty());
        gameAwardId.setCellValueFactory (
            cellData -> cellData.getValue().getAwardIdProperty());
        gameMoneyCollected.setCellValueFactory (
            cellData -> cellData.getValue().getMoneyCollectedProperty());
        gameAwardDate.setCellValueFactory (
            cellData -> cellData.getValue().getAwardDateProperty());
        gameAwardPrice.setCellValueFactory (
            cellData -> cellData.getValue().getAwardPriceProperty());
        
        showGameHistoryDatails(null);
        
        gameHistoryTable.getSelectionModel().selectedItemProperty().addListener(
                 (observable, oldValue, newValue) -> showGameHistoryDatails(newValue));
    }
    
}
