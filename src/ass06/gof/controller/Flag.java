package ass06.gof.controller;

/**
 * Created by Luca on 21/05/16.
 */

//Monitor utilizzato per avviare e fermare le iterazioni del ciclo della vita
public class Flag {

    private boolean value;

    public Flag(){
        this.value = false;
    }

    public synchronized void setValue(boolean value){
        this.value = value;
    }

    public synchronized boolean getValue(){
        return this.value;
    }
}
