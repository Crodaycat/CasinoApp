/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author luis.giraldo10
 */
public class Machine {
    private StringProperty serie;
    private StringProperty type;

    public Machine(String serie, String type) {
        this.serie = new SimpleStringProperty(serie);
        this.type = new SimpleStringProperty(type);
    }

    public String getSerie() {
        return serie.get();
    }

    public void setSerie(StringProperty serie) {
        this.serie = serie;
    }

    public String getType() {
        return type.get();
    }

    public void setType(StringProperty type) {
        this.type = type;
    }
    
    public StringProperty getSerieProperty() {
        return serie;
    }
    
    public StringProperty getTypeProperty() {
        return type;
    }
}
