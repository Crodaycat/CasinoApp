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

@XmlRootElement(name = "Awards")
public class AwardListWrapper {
    private List<Award> awards;
    @XmlElement(name = "Award")
    public List<Award> getAward() {
        return awards;
    }
    
    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
}