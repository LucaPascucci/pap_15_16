package ass07.tasks.controller;

/**
 * Created by Luca on 28/05/16.
 */
public class WinnerFlag {

    private boolean value;
    private int playerNumber;

    public WinnerFlag(){
        this.value = false;
    }

    public synchronized void setWinner(int value) {
        this.playerNumber = value;
        this.value = true;
    }

    public synchronized boolean getValue(){
        return this.value;
    }

    public synchronized int getWinner(){
        return this.playerNumber;
    }
}
