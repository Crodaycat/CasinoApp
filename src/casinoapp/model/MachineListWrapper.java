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
 *
 * @author USUARIO
 */

@XmlRootElement(name = "Machines")
public class MachineListWrapper {
    private List<Machine> machines;

    @XmlElement(name = "Machine")
    public List<Machine> getMachine() {
        return machines;
    }
    
    public void setDealers(List<Machine> machines) {
        this.machines = machines;
    }
}