/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import casinoapp.MainApp;
import casinoapp.model.Dealer;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Tab;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {
    
    @FXML
    private Tab dealerTab;
    @FXML
    private Tab machinesTab;
    @FXML
    private Tab machinesListTab;
    @FXML
    private Tab machinesAwardsTab;
    @FXML
    private Tab machinesGamesHistoryTab;
    
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        if(dealerTab.isSelected()){
        mainApp.getDealerData().clear();
        mainApp.setDealerFilePath(null);
        }
        if(machinesTab.isSelected()){
        
        mainApp.getMachineData().clear();
        mainApp.setMachineFilePath(null);
        }
         if( machinesAwardsTab.isSelected()){
             mainApp.getAwardData().clear();
             mainApp.setAwardFilePath(null);
         }
         if(machinesGamesHistoryTab.isSelected()){
             mainApp.getGameHistoryData().clear();
             mainApp.setGameHistoryFilePath(null);
         }
    
    }
    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if(dealerTab.isSelected()) mainApp.loadDealerDataFromFile(file);
            if(machinesListTab.isSelected()) mainApp.loadDataFromFileMachine(file);
            if(machinesAwardsTab.isSelected()) mainApp.loadDataFromFileAward(file);
            if(machinesGamesHistoryTab.isSelected()) mainApp.loadDataFromFileGameHistory(file);
            
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
      
        if(dealerTab.isSelected()){
        File dealerFile = mainApp.getDealerFilePath();
        if (dealerFile != null) { mainApp.saveDealerDataToFile(dealerFile);
        } else { handleSaveAs();}
        }
        if(machinesListTab.isSelected()){
            File machineFile = mainApp.getMachineFilePath();
        if (machineFile!= null) {
            mainApp.saveMachineDataToFile(machineFile);
        } else {handleSaveAs();}    
        }
        if(machinesAwardsTab.isSelected()){
            File machineFile = mainApp.getAwardFilePath();
        if (machineFile!= null) {
            mainApp.saveMachineAwardsDataToFile(machineFile);
        } else {handleSaveAs();}    
        }
        if(machinesGamesHistoryTab.isSelected()){
            File machineFile = mainApp.getGameHistoryFilePath();
        if (machineFile!= null) {
            mainApp.saveMachineGameHistoryDataToFile(machineFile);
        } else {handleSaveAs();}    
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            if(dealerTab.isSelected())mainApp.saveDealerDataToFile(file);
            if(machinesListTab.isSelected())mainApp.saveMachineDataToFile(file);
            if(machinesAwardsTab.isSelected()) mainApp.saveMachineAwardsDataToFile(file);
            if(machinesGamesHistoryTab.isSelected()) mainApp.saveMachineGameHistoryDataToFile(file);
    }
    }
    

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

        alert.showAndWait();
    }
    

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
        private void handleShowBirthdayStatistics() {
     mainApp.showDateStatistics();
}

    public Tab getDealerTab() {
        return dealerTab;
    }

    public Tab getMachineTab() {
        return machinesTab;
    }
}
