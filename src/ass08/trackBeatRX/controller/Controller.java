package ass08.trackBeatRX.controller;

import ass08.trackBeatRX.view.MainView;
import pap.ass08.pos.Flag;

/**
 * Created by Luca on 09/06/16.
 */
public class Controller implements IController{

    private MainView view;
    private Flag flag;

    public Controller (MainView view){
        this.view = view;
        this.flag = new Flag(); //valore false
    }

    @Override
    public void started() {
        //TODO Avviare sensore battito cardiaco e posizione
    }

    @Override
    public void stopped() {
        this.flag.set(); // valore true
    }
}
