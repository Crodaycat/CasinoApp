/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import casinoapp.util.LocalDateAdapter;
import java.time.LocalDate;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author USUARIO
 */
public class GameHistory {
    private StringProperty machineSerie;
    private StringProperty awardId;
    private StringProperty moneyCollected;
    private ObjectProperty<LocalDate> awardDate;
    private StringProperty awardPrice;

    public GameHistory(String machineSerie, String awardId, String moneyCollected, LocalDate awardDate, String awardPrice){
        this.machineSerie = new SimpleStringProperty(machineSerie);
        this.awardId = new SimpleStringProperty(awardId);
        this.moneyCollected = new SimpleStringProperty(moneyCollected);
        this.awardDate = new SimpleObjectProperty<>(awardDate);
        this.awardPrice = new SimpleStringProperty(awardPrice);
    }

    public StringProperty getMachineSerieProperty() {
        return machineSerie;
    }

    public void setMachineSerie(String machineSerie) {
        this.machineSerie.set(machineSerie);
    }

    public StringProperty getAwardIdProperty() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId.set(awardId);
    }

    public StringProperty getMoneyCollectedProperty() {
        return moneyCollected;
    }

    public void setMoneyCollected(String moneyCollected) {
        this.moneyCollected.set(moneyCollected);
    }

    public ObjectProperty<LocalDate> getAwardDateProperty() {
        return awardDate;
    }

    public void setAwardDate(LocalDate awardDate) {
        this.awardDate.set(awardDate);
    }

    public StringProperty getAwardPriceProperty() {
        return awardPrice;
    }

    public void setAwardPrice(String awardPrice) {
        this.awardPrice.set(awardPrice);
    }

    public String getMachineSerie() {
        return machineSerie.get();
    }

    public String getAwardId() {
        return awardId.get();
    }

    public String getMoneyCollected() {
        return moneyCollected.get();
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getAwardDate() {
        return awardDate.get();
    }

    public String getAwardPrice() {
        return awardPrice.get();
    }
    
    
}
