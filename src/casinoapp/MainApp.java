package casinoapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import casinoapp.model.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;



public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootLayoutController rootLayoutController;
    
    private ObservableList<Dealer> dealerData = FXCollections.observableArrayList();
    private ObservableList<Machine> machineData = FXCollections.observableArrayList();
    private ObservableList<Award> awardData = FXCollections.observableArrayList();
    private ObservableList<GameHistory> gameHistoryData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        machineData.add(new Machine("01", "02"));
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Dealer> getDealerData() {
        return dealerData;
    }
    
    public ObservableList<Machine> getMachineData() {
        return machineData;
    }
    
    public ObservableList<Award> getAwardData() {
        return awardData;
    }
    
    public ObservableList<GameHistory> getGameHistoryData() {
        return gameHistoryData;
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CasinoApp");
        
        this.primaryStage.getIcons().add(new Image("file:images/address_book_32.png"));

        initRootLayout();
        
        showDealerOverview(rootLayoutController.getDealerTab());
        showMachineOverview(rootLayoutController.getMachineTab());
    }

    /**
     * Initializes the root layout.
     */
   /**
 * Initializes the root layout and tries to load the last opened
 * person file.
 */
public void initRootLayout() {
    try {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        rootLayoutController = loader.getController();
        rootLayoutController.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Try to load last opened person file.
    File file = getDealerFilePath();
    if (file != null) {
        loadDealerDataFromFile(file);
    }
    File fileMachine = getMachineFilePath();
    if (fileMachine != null) {
        loadDataFromFileMachine(fileMachine);
    }
    File fileAward = getAwardFilePath();
    if (fileAward != null) {
        loadDataFromFileAward(fileAward);
    }
    File fileGameHistory = getGameHistoryFilePath();
    if (fileGameHistory != null) {
        loadDataFromFileGameHistory(fileGameHistory);
    }
}

    /**
     * Shows the person overview inside the root layout.
     */
public void showDealerOverview(Tab tab) {
    try {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DealerOverview.fxml"));
        AnchorPane dealerOverview = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        tab.setContent(dealerOverview);

        // Give the controller access to the main app.
        DealerOverviewController controller = loader.getController();
        controller.setMainApp(this);

    } catch (IOException e) {
        e.printStackTrace();
    }  
}

public void showMachineOverview (Tab tab) {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/MachineOverview.fxml"));
        AnchorPane machineOverview = (AnchorPane) loader.load();
        
        tab.setContent(machineOverview);
        
        MachineOverviewController controller = loader.getController();
        controller.setMainApp(this);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public boolean showDealerEditDialog(Dealer dealer) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DealerEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Dealer");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        DealerEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setDealer(dealer);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
/**
 * Returns the person file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */


public boolean showMachineEditDialog(Machine machine) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/MachineEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Machine");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        MachineEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMachine(machine);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean showAwardEditDialog(Award award) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/AwardsEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Award");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        AwardsEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAward(award);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public File getDealerFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

public File getMachineFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePathMachine", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

public File getAwardFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePathAward", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

public File getGameHistoryFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePathGameHistory", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setDealerFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("CasinoApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Update the stage title.
        primaryStage.setTitle("CasinoApp");
    }
}

public void setMachineFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePathMachine", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("CasinoApp - " + file.getName());
    } else {
        prefs.remove("filePathMachine");

        // Update the stage title.
        primaryStage.setTitle("CasinoApp");
    }
}

public void setAwardFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePathAward", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("CasinoApp - " + file.getName());
    } else {
        prefs.remove("filePathAward");

        // Update the stage title.
        primaryStage.setTitle("CasinoApp");
    }
}

public void setGameHistoryFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePathGameHistory", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("CasinoApp - " + file.getName());
    } else {
        prefs.remove("filePathGameHistory");

        // Update the stage title.
        primaryStage.setTitle("CasinoApp");
    }
}

/**
 * Loads person data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
 */
public void loadDealerDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(DealerListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        DealerListWrapper wrapper = (DealerListWrapper) um.unmarshal(file);

        dealerData.clear();
        dealerData.addAll(wrapper.getDealers());

        // Save the file path to the registry.
        setDealerFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load Dealers data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());
        System.out.println(e.toString());
        alert.showAndWait();
    }
}

public void loadDataFromFileMachine (File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(MachineListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        MachineListWrapper wrapper = (MachineListWrapper) um.unmarshal(file);

        machineData.clear();
        machineData.addAll(wrapper.getMachine());

        // Save the file path to the registry.
        setDealerFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load Machines data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());
        
        System.out.println(e.toString());
        
        alert.showAndWait();
    }
}

public void loadDataFromFileAward (File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(AwardListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        AwardListWrapper wrapper = (AwardListWrapper) um.unmarshal(file);

        awardData.clear();
        awardData.addAll(wrapper.getAward());

        // Save the file path to the registry.
        setDealerFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load Awards data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());
        System.out.println(e.toString());
        alert.showAndWait();
    }
}

public void loadDataFromFileGameHistory (File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(GameHistoryListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        GameHistoryListWrapper wrapper = (GameHistoryListWrapper) um.unmarshal(file);

        gameHistoryData.clear();
        gameHistoryData.addAll(wrapper.getGameHistory());

        // Save the file path to the registry.
        setDealerFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load GameHistory data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}

/**
 * Saves the current person data to the specified file.
 * 
 * @param file
 */
public void saveDealerDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(DealerListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        DealerListWrapper wrapper = new DealerListWrapper();
        wrapper.setDealers(dealerData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setDealerFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
    }
}
public void showDateStatistics() {
    try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DateStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Date Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        DateStatisticsController controller = loader.getController();
        controller.setDealerData(dealerData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}