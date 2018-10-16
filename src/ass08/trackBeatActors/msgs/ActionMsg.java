package ass08.trackBeatActors.msgs;

import ass08.trackBeatActors.model.EAction;

/**
 * Created by Luca on 25/06/16.
 */

//Messagi di avvio,stop,reset,aggiornamenti TH,richiesta nuovi dati
public class ActionMsg {

    private EAction action;

    //Utilizzati per gestire la modifica runtime dei TH
    private int value;
    private boolean restore_update; //true = aggiornare / false = ripristinare

    public ActionMsg (EAction action){
        this.action = action;
    }

    public ActionMsg(EAction action, int value){
        this.action = action;
        this.value = value;
    }

    public ActionMsg (EAction action, int value, boolean r_u){
        this.action = action;
        this.value = value;
        this.restore_update = r_u;
    }

    public EAction getAction() {
        return this.action;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isRestore_update() {
        return this.restore_update;
    }
}
