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

@XmlRootElement(name = "GameHistories")
public class GameHistoryListWrapper {
    private List<GameHistory> gameHistories;
    @XmlElement(name = "GameHistory")
    public List<GameHistory> getGameHistory() {
        return gameHistories;
    }
    
    public void setDealers(List<GameHistory> gameHistories) {
        this.gameHistories = gameHistories;
    }
}
