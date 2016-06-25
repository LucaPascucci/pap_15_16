package ass08.trackBeatRX.controller;

/**
 * Created by Luca on 09/06/16.
 */
public interface IController {

    //Avvia il monitoraggio
    void started();

    //Ferma il monitoraggio
    void stopped();

    //Ripristina tutti i dati
    void reset();

    //modifica o ripristina heartbeat TH
    void modifyHBTH(boolean action, int value);

    //modifica o ripristina sec TH
    void modifySecTH(boolean action, int value);
}
