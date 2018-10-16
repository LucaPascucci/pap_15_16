package ass07.tasks.monitor;

/**
 * Created by Luca on 28/05/16.
 */
//Monitor per gestire la selezione del vincitore
public class WinnerFlag {

    private boolean winner;
    private int playerNumber;

    public WinnerFlag(){
        this.winner = false;
    }

    public synchronized boolean isWinnerAvailable(){
        return this.winner;
    }

    public synchronized int getWinnerNumber(){
        return this.playerNumber;
    }

    //Controlla se il player è il vincitore in base al suggerimento
    public synchronized void checkWinner(int hint, int playerNumber){
        if (!this.winner && hint == 0){
            this.winner = true;
            this.playerNumber = playerNumber;
        }
    }
}
