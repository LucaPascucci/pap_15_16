package ass07.tasks.monitor;

/**
 * Created by Luca on 28/05/16.
 */

//Monitor utilizzato per gestire start e stop del gioco
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
