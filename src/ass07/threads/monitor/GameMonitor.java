package ass07.threads.monitor;

/**
 * Created by Luca on 29/05/16.
 */

//monitor che gestisce avvio e stop del gioco + vincitore + barriera per gestire i turni
public class GameMonitor {

    private boolean activeGame;
    private boolean winner;
    private int turnAttempts,playersNumber,turn;

    public GameMonitor(int playersNumber){
        this.activeGame = false;
        this.winner = false;
        this.turnAttempts = 0;
        this.playersNumber = playersNumber;
        this.turn = 1;
    }

    public synchronized void setActiveGame(boolean value){
        this.activeGame = value;
    }

    public synchronized boolean isActiveGame(){
        return this.activeGame;
    }

    //Barriera che sbocca i giocatori quando tutti hanno effettuato il tentativo del turno
    public synchronized void nextTurn(){
        this.turnAttempts++;
        if(this.turnAttempts == this.playersNumber){
            this.turnAttempts = 0;
            this.turn++;

            if (this.winner){ //quando è stato decretato il vincitore ferma il gioco alla fine del turno
                this.activeGame = false;
            }

            notifyAll(); //avvia il prossimo turno sbloccando tutti i thread in wait
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int getTurn(){
        return this.turn;
    }

    //controllo se il thread ha indovinato il numero
    public synchronized boolean isWinner(int hint){
        if (hint == 0 && !this.winner){ //controllo che non sia gia stato assegnato già un vincitore
            this.winner = true;
            return true;
        }
        return false;
    }
}
