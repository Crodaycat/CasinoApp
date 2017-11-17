/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jcarolina.escobar
 */
public class Dealer extends Person{
    private final FloatProperty baseMoney; 
    private final FloatProperty workedhours; 
    private final StringProperty financialProfit; 
    public final float hourWorkedPrice=30000;
    

    public Dealer(float baseMoney, float workedhours, String firstName, String lastName, String id) {
        super(firstName, lastName, id);
        this.baseMoney = new SimpleFloatProperty(baseMoney);
        this.workedhours = new SimpleFloatProperty(workedhours);
        this.financialProfit = new SimpleStringProperty("0");
     
    }

    public Dealer() {
        this(0, 0, null, null, null);
    }

    public FloatProperty BaseMoneyProperty() {
        return baseMoney;
    }

    public FloatProperty WorkedhoursProperty() {
        return workedhours;
    }

    public StringProperty FinancialProfitProperty() {
        return financialProfit;
    }

    
    public float getBaseMoney() {
        return baseMoney.get();
    }

    public float  getWorkedhours() {
        return workedhours.get();
    }

    public String getFinancialProfit() {
        return financialProfit.get();
    }

    
    public void setBaseMoney(float baseMoney) {
        this.baseMoney.set(baseMoney);
    }

    public void  setWorkedhours(float workedHours) {
        this.workedhours.set(workedHours);
    }

    public void  setFinancialProfit(String financialProfit) {
        this.financialProfit.set(financialProfit);
    }
}
