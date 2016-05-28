package ass07.tasks.controller;

/**
 * Created by Luca on 28/05/16.
 */

//Monitor utilizzato per avviare e fermare le iterazioni del ciclo della vita
public interface IController {

    //Avvia il gioco
    void started();

    //Ferma il gioco
    void stopped();

    //Resetta tutti i valori del gioco
    void reset();

}
