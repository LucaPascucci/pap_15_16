package ass07.tasks.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Luca on 28/05/16.
 */

//Rappresenta un giocatore. Contiene numero giocatore, intervallo(max,min) e numero generato ogni turno
public class PlayerData {

    private int playerNumber;
    private int max;
    private int min;
    private int attempt;

    public PlayerData(int playerNumber, int max, int min){
        this.playerNumber = playerNumber;
        this.max = max;
        this.min = min;
    }

    public PlayerData(PlayerData playerData){
        this.attempt = playerData.attempt;
        this.max = playerData.max;
        this.min = playerData.min;
        this.playerNumber = playerData.playerNumber;
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }

    //Genera un nuovo numero all'interno dell'intervallo
    public int getNextAttempt(){
        this.attempt = ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
        return this.attempt;
    }

    //in base al suggerimento decide di modificare il margine superiore o inferiore
    public void receiveHint(int hint){
        /*
         * Hint < 0 allora il numero da indovinare è minore del tentativo quindi, utilizzo il tentativo per abbassare il MAX del range di generazione del prossimo tentativo
         * Hint > 0 allora il numero da indovinare è maggiore del tentativo quindi, utilizzo il tentativo per aumentare il MIN del range di generazione del prossimo tentativo
         */
        if (hint < 0){
            this.max = this.attempt;
        } else if (hint > 0){
            this.min = this.attempt;
        }
    }

    @Override
    public String toString() {
        return "Player-" + this.playerNumber + " attempt: " + this.attempt + " -> Range: " + this.max + " / " + this.min;

    }
}
