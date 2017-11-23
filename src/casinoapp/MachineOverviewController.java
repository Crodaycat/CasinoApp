/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp;

import casinoapp.model.Award;
import casinoapp.model.Dealer;
import casinoapp.model.GameHistory;
import casinoapp.model.Machine;
import casinoapp.util.DateUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import javafx.collections.transformation.FilteredList;
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
        Machine machine = machinesTable.getSelectionModel().getSelectedItem();
        
        if (machine != null) {
            FilteredList<Award> machineAwards = filterAwardsByMachineSerie(machine.getType());
            if (machineAwards.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No history Found and no Awards Found");
                alert.setHeaderText(null);
                alert.setContentText("Please create awards for this machine Type.");
                alert.showAndWait();
            } else {
                FilteredList<GameHistory> machineHistory = filterHistoriesByDateAndSerie(machine.getSerie(), LocalDate.now());
                if (machineHistory.isEmpty()) {
                    
                } else {
                    makePDF(machine, machineHistory, machineAwards);
                }
            }
        } else {
            // Nothing selected.
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("No Selection");
           alert.setHeaderText(null);
           alert.setContentText("Please select a Machine in the table.");
           alert.showAndWait();
        }
    }
    
    private FilteredList<Award> filterAwardsByMachineSerie (String type) {
        FilteredList<Award> filteredAwards = new FilteredList<>(mainApp.getAwardData(), award -> award.getMachineType().equals(award));
        return filteredAwards;
    }
    
    private FilteredList<GameHistory> filterHistoriesByDateAndSerie (String machineSerie, LocalDate date) {
        FilteredList<GameHistory> filteredHistories = new FilteredList<>(mainApp.getGameHistoryData(), 
                history -> history.getMachineSerie().equals(machineSerie) && history.getAwardDate().equals(date));
        return filteredHistories;
    }
    
    private FilteredList<GameHistory> makeHistory () {
        return;
    }
    
    private void makePDF (Machine machine, FilteredList<GameHistory> history, FilteredList<Award> awards) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nom = machine.getSerie()+"-"+machine.getType();
        try {
            FileOutputStream archivo = new FileOutputStream("PDF/Machine"+ nom + ".pdf");
            Document doc = new Document();

            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setFont(FontFactory.getFont("Times New Roman", 24, Font.BOLD, BaseColor.RED));
            titulo.add("INVENTARIO\n\n");
            titulo.add("Maquína con número de serie " + machine.getSerie()+"\n");
            titulo.add("Tipo " + machine.getType() + "\n");
            titulo.add(LocalDate.now().toString() + "\n\n");
            
            try {
                doc.add(titulo);
                
            } catch (Exception e) {
            }
            
            
            //Set table column number, width of the table and width of each column
            PdfPTable table = new PdfPTable(4); 
            table.setWidthPercentage(100);
            table.setWidths(new float[] {40, 20, 20, 20});
            
            Paragraph column = new Paragraph("Descripción");
            column.getFont().setStyle(Font.BOLD);
            column.getFont().setSize(10);
            table.addCell(column);
            
            column = new Paragraph("Dinero adquirido");
            column.getFont().setStyle(Font.BOLD);
            column.getFont().setSize(10);
            table.addCell(column);
            
            column = new Paragraph("Valor del premio");
            column.getFont().setStyle(Font.BOLD);
            column.getFont().setSize(10);
            table.addCell(column);
            
            column = new Paragraph("Subtotal");
            column.getFont().setStyle(Font.BOLD);
            column.getFont().setSize(10);
            table.addCell(column);
            
            double total = 0;
            for (GameHistory award : history) {
                FilteredList<Award> awardInfo = new FilteredList<> (awards, item -> item.getAwardId().equals(award.getAwardId()));
                table.addCell(new Paragraph(awardInfo.get(0).getDescription()));
                float collected = Float.parseFloat(award.getMoneyCollected());
                float price = Float.parseFloat(award.getAwardPrice());
                float subtotal = collected - price;
                total += subtotal;
                table.addCell(new Paragraph(String.valueOf(collected)));
                table.addCell(new Paragraph(String.valueOf(price)));
                table.addCell(new Paragraph(String.valueOf(subtotal)));
            }
            
            table.addCell(new Paragraph(""));
            table.addCell(new Paragraph(""));
            column = new Paragraph("Total:");
            column.getFont().setStyle(Font.BOLD);
            column.getFont().setSize(10);
            table.addCell(column);
            table.addCell(new Paragraph(String.valueOf(total)));
            
            doc.add(table);
            
            doc.close();
            PdfWriter.getInstance(doc, archivo);
        } catch (Exception a) {

        }
        String tmp="El archivo fue generado en la Ruta PDF/Machine"+nom+".pdf";
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fichero PDF creado con exito");
        alert.setHeaderText(null);
        alert.setContentText(tmp);
        alert.show();
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
        machinesTable.setItems(mainApp.getMachineData());
        awardsTable.setItems(mainApp.getAwardData());
        gameHistoryTable.setItems(mainApp.getGameHistoryData());
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
