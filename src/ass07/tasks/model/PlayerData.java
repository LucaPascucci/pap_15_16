package ass07.tasks.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Luca on 28/05/16.
 */

//Rappresenta un giocatore. Contiene numero giocatore, intervallo e numero generato ogni turno
public class PlayerData {

    private int playerNumber;
    private int max;
    private int min;
    private int lastNumber;

    public PlayerData(int playerNumber, int max, int min){
        this.playerNumber = playerNumber;
        this.max = max;
        this.min = min;
    }

    public PlayerData(PlayerData playerData){
        this.lastNumber = playerData.lastNumber;
        this.max = playerData.max;
        this.min = playerData.min;
        this.playerNumber = playerData.playerNumber;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

    //genera un nuovo numero all'interno dell'intervallo
    public int getNextAttempt(){
        this.lastNumber = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
        return this.lastNumber;
    }

    //in base al suggerimento decide di modificare il margine superiore o inferiore
    public void receiveHint(int hint){
        if (hint < 0){
            this.max = this.lastNumber;
        } else if (hint > 0){
            this.min = this.lastNumber;
        }
    }

    @Override
    public String toString() {
        return "Player-" + this.playerNumber + " attempt: " + this.lastNumber + " -> Range: " + this.max + " / " + this.min;

    }
}
