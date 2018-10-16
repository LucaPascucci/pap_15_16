package ass08.trackBeatRX.controller;

/**
 * Created by Luca on 09/06/16.
 */
public interface IController {

    //Avvia il monitoraggio
    void start();

    //Ferma il monitoraggio
    void stop();

    //Ripristina tutti i dati
    void reset();

    //modifica o ripristina heartbeat TH solo se action == true
    void modifyHBTH(boolean action, int value);

    //modifica o ripristina sec TH solo se action == true
    void modifySecTH(boolean action, int value);
}
