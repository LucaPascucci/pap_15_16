package ass07.tasks.controller;

/**
 * Created by Luca on 28/05/16.
 */
public class TurnFlag {

    private boolean value;

    public TurnFlag(){
        this.value = false;
    }

    public synchronized void setValue(boolean value){
        this.value = value;
    }

    public synchronized boolean getValue(){
        return this.value;
    }
}
