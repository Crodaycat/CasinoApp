/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import casinoapp.util.LocalDateAdapter;
import java.time.LocalDate;
import java.time.Month;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author luis.giraldo10
 */
public class Machine {
    private final StringProperty serie;
    private final ObjectProperty<LocalDate> awardDate;
    private final FloatProperty moneyCollected;
    private final FloatProperty price;
    private final FloatProperty award;

    public Machine(String serie, float moneyCollected, float price, float award) {
        this.serie = new SimpleStringProperty(serie);
        this.awardDate =  new SimpleObjectProperty<>(LocalDate.of(2017, Month.MARCH, 10));
        this.moneyCollected = new SimpleFloatProperty(moneyCollected);
        this.price = new SimpleFloatProperty(price);
        this.award = new SimpleFloatProperty(award);
    }

    public Machine() {
        this("",0,0,0);
    }
     public void setSerie(String serie) {
        this.serie.set(serie);
    }

    public void  setMoneyCollected(float moneyCollected) {
        this.moneyCollected.set(moneyCollected);
    }

    public void  setPrice(float price) {
        this.price.set(price);
    }
    public void  setAward(float award) {
        this.award.set(award);
    }
      
     public float  getaward() {
        return award.get();
    }
    
    public float getMoneyCollected() {
        return moneyCollected.get();
    }

    public float  getPrice() {
        return price.get();
    }
    public String  getSerie() {
        return serie.get();
    }
     public float  getAward() {
        return award.get();
    }
  
     @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getAwardDate() {
        return awardDate.get();
}

    public void setAwardDate(LocalDate date) {
        this.awardDate.set(date);
    }

    public ObjectProperty<LocalDate> awardDateProperty() {
        return awardDate;
    }

    public StringProperty getSerieProperty() {
        return serie;
    }
     public FloatProperty getAwardProperty() {
        return award;
    }
      public FloatProperty getPriceProperty() {
        return price;
    }
       public  FloatProperty getmoneyCollectedProperty() {
        return  moneyCollected;
    }

  
}
