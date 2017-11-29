/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import casinoapp.model.Dealer;
import casinoapp.model.Machine;
import casinoapp.util.DateUtil;
import casinoapp.util.LocalDateAdapter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;
import java.util.Random;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

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
    private TableColumn<Machine, String> machineAward;
    @FXML
    private TableColumn<Machine, String> machineMoneyCollected;
    @FXML
    private TableColumn<Machine, String> machineAwardPrice;
    @FXML
    private Label machineMoneyLabel;
    @FXML
    private Label machineDateLabel;
    @FXML
    private Label machinePriceLabel;
    @FXML
    private Label machineSerieLabel;
    @FXML
    private Label machineAwardLabel;
    
    private MainApp mainApp;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        machineSerie.setCellValueFactory (
            cellData -> cellData.getValue().getSerieProperty());
        machineAward.setCellValueFactory (
            cellData -> cellData.getValue().getAwardProperty().asString());
        machineMoneyCollected.setCellValueFactory (
            cellData -> cellData.getValue().getmoneyCollectedProperty().asString());
       
        machineAwardPrice.setCellValueFactory (
            cellData -> cellData.getValue().getPriceProperty().asString());
      
            showMachineDetails(null);

         // Listen for selection changes and show the person details when changed.
         machinesTable.getSelectionModel().selectedItemProperty().addListener(
                 (observable, oldValue, newValue) -> showMachineDetails(newValue));
        
        try {
            machinesTable.setItems(mainApp.getMachineData());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        //awardsTable.setItems(mainApp.getAwardData());
        //gameHistoryTable.setItems(mainApp.getGameHistoryData());
    }    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        machinesTable.setItems(mainApp.getMachineData());
    }
    
   private void showMachineDetails(Machine machine) {
    if (machine!= null) {
        // Fill the labels with info from the person object.
        machineSerieLabel.setText(machine.getSerie());
        machineAwardLabel.setText(String.valueOf(machine.getAward()));
        machineMoneyLabel.setText(String.valueOf(machine.getMoneyCollected()));
        machineDateLabel.setText(DateUtil.format(machine.getAwardDate()));
        machinePriceLabel.setText(String.valueOf(machine.getPrice()));
        
    } else {
        // Person is null, remove all the text.
        machineSerieLabel.setText("");
        machineAwardLabel.setText("");
        machineMoneyLabel.setText("");
        machineDateLabel.setText("");
        machinePriceLabel.setText("");
    }
   }
    
    @FXML
    private void handleDeleteMachine() {
    int selectedIndex = machinesTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        machinesTable.getItems().remove(selectedIndex);
    } else {
        // Nothing selected.
       Alert alert = new Alert(AlertType.WARNING);
       alert.setTitle("No Selection");
       alert.setHeaderText(null);
       alert.setContentText("Please select a machine in the table.");
       alert.showAndWait();
    }
}
    /**
 * Called when the user clicks the new button. Opens a dialog to edit
 * details for a new person.
 */
@FXML
private void handleNewMachine() {
    Machine tempMachine = new Machine();
    boolean okClicked = mainApp.showMachineEditDialog(tempMachine);
    if (okClicked) {
        mainApp.getMachineData().add(tempMachine);
    }
}

/**
 * Called when the user clicks the edit button. Opens a dialog to edit
 * details for the selected person.
 */
@FXML
private void handleEditMachine() {
    Machine selectedMachine = machinesTable.getSelectionModel().getSelectedItem();
    if (selectedMachine != null) {
        boolean okClicked = mainApp.showMachineEditDialog(selectedMachine);
        if (okClicked) {
            showMachineDetails(selectedMachine);
        }

    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Machine Selected");
        alert.setContentText("Please select a machine in the table.");

        alert.showAndWait();
    }
}
@FXML
    private void handleExportPDF() throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        FileWriter fichero = null;
        PrintWriter pw = null;
        Machine e = (machinesTable.getSelectionModel().getSelectedItem());
        if (e == null) {

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Seleccionó");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione una maquina de la tabla.");
            alert.showAndWait();
        } else {
            String nom = e.getSerie()+e.getAwardDate();

            try {
                FileOutputStream archivo = new FileOutputStream(file+".pdf");
                Document doc = new Document();

                PdfWriter.getInstance(doc, archivo);
                doc.open();
                doc.add(new Paragraph("Machine Serie:"+e.getSerie()+"\n"+
                        "Award money"+e.getAward()+" "+"\n"+"Day of award"+e.getAward()+"\n"
                +"House's profit"+e.getPrice()+"\n"+"Total Money Collected: "+e.getMoneyCollected()));
                doc.close();
                PdfWriter.getInstance(doc, archivo);
            } catch (Exception a) {

            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Fichero PDF creado con exito");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    
    
}
