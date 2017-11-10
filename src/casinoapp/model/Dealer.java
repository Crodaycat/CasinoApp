/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import java.util.ArrayList;
import javafx.beans.property.FloatProperty;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Tatiana
 */
public class Dealer extends Person{
    private final FloatProperty baseMoney; 
    private final FloatProperty workedhours; 
    private final FloatProperty moneyIn; 
    private final FloatProperty moneyOut; 

    public Dealer(float baseMoney, float workedhours, float moneyIn, float moneyOut, String firstName, String lastName, String id) {
        super(firstName, lastName, id);
        this.baseMoney = new SimpleFloatProperty(baseMoney);
        this.workedhours = new SimpleFloatProperty(workedhours);
        this.moneyIn = new SimpleFloatProperty(moneyIn);
        this.moneyOut = new SimpleFloatProperty(moneyOut);
    }

 

   

    public FloatProperty getBaseMoney() {
        return baseMoney;
    }

    public FloatProperty getWorkedhours() {
        return workedhours;
    }
    
    public FloatProperty getMoneyOut() {
        return moneyOut;
    }
    
    public void setbaseMoney(float baseMoney) {
        this.baseMoney.set(baseMoney);
    }
    
    public void setWorkedHours(float workedHours) {
        this.workedhours.set(workedHours);
    }
    
     public void setMoneyIn(float moneyIn) {
        this.moneyIn.set(moneyIn);
    }
     
      public void setMoneyOut(float moneyOut) {
        this.moneyOut.set(moneyOut);
    }

    public FloatProperty getMoneyIn() {
        return moneyIn;
    }
}
