package ass07.tasks.monitor;

/**
 * Created by Luca on 28/05/16.
 */
//Monitor per gestire la selezione del vincitore
public class WinnerFlag {

    private boolean value;
    private int playerNumber;

    public WinnerFlag(){
        this.value = false;
    }

    public synchronized boolean getValue(){
        return this.value;
    }

    public synchronized int getWinner(){
        return this.playerNumber;
    }

    //Controlla se il player è il vincitore in base al suggerimento
    public synchronized void isWinner(int hint, int playerNumber){
        if (!this.value && hint == 0){
            this.value = true;
            this.playerNumber = playerNumber;
        }
    }
}
