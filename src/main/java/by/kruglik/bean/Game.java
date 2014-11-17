package by.kruglik.bean;

import java.io.Serializable;

/**
 * Created by kruglik on 17.11.2014.
 */
public class Game implements Serializable{
    private Integer id;
    private Integer numberPlayers;
    private Integer currentNumberPlayers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(Integer numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public Integer getCurrentNumberPlayers() {
        return currentNumberPlayers;
    }

    public void setCurrentNumberPlayers(Integer currentNumberPlayers) {
        this.currentNumberPlayers = currentNumberPlayers;
    }
}
