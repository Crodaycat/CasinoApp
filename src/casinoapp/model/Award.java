/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USUARIO
 */
public class Award {
    private StringProperty awardId;
    private StringProperty machineType;
    private StringProperty description;
    private StringProperty price;

    public Award(String awardId, String machineType, String description, String price) {
        this.awardId = new SimpleStringProperty(awardId);
        this.machineType = new SimpleStringProperty(machineType);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleStringProperty(price);
    }
    
    public Award () {
        awardId = new SimpleStringProperty(null);
        machineType = new SimpleStringProperty(null);
        description = new SimpleStringProperty(null);
        price = new SimpleStringProperty(null);
    }

    public StringProperty getAwardIdProperty() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId.set(awardId);
    }

    public StringProperty getMachineTypeProperty() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType.set(machineType);
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty getPriceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getAwardId() {
        return awardId.get();
    }

    public String getMachineType() {
        return machineType.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getPrice() {
        return price.get();
    }
}


