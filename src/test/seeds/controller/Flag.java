package test.seeds.controller;

/**
 * Created by Luca on 21/05/16.
 */

//Monitor utilizzato per avviare e fermare l'algoritmo
public class Flag {

    private boolean value;

    public Flag(){
        this.value = true;
    }

    public synchronized void setValue(boolean value){
        this.value = value;
    }

    public synchronized boolean getValue(){
        return this.value;
    }
}
