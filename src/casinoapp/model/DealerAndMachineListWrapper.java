/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casinoapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "Dealers")
public class DealerAndMachineListWrapper {

    private List<Dealer> dealers;
    private List<Machine> machines;

    @XmlElement(name = "dealer")
    public List<Dealer> getDealers() {
        return dealers;
    }
    
    public void setDealers(List<Dealer> dealers) {
        this.dealers= dealers;
    }
    
    @XmlElement(name = "machine")
    public List<Machine> getMachines () {
        return machines;
    }
     
    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    
}