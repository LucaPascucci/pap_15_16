package ass06.gof.controller;


/**
 * Created by Luca on 20/05/16.
 */
public interface IController {

    //Avvia il ciclo della vita
    void started();

    //Ferma il ciclo della vita
    void stopped();

    //Ripristina il mondo allo stato iniziale(vuoto)
    void reset();

    //Aggiunge o rimuove i seed scelti dall'utente
    void manageSeed(int r, int c, boolean value);

    //Cambio delle regole del ciclo della vita
    void changeRules();
}
