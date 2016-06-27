package ass08.trackBeatActors.msgs;

import ass08.trackBeatActors.model.EnumAction;

/**
 * Created by Luca on 25/06/16.
 */
public class ActionMsg {

    private EnumAction action;

    //Utilizzati per gestire la modifica runtime dei TH
    private int value;
    private boolean restore_update; //true = aggiornare - false = ripristinare

    public ActionMsg (EnumAction action){
        this.action = action;
    }

    public ActionMsg(EnumAction action, int value){
        this.action = action;
        this.value = value;
    }

    public ActionMsg (EnumAction action, int value, boolean r_u){
        this.action = action;
        this.value = value;
        this.restore_update = r_u;
    }

    public EnumAction getAction() {
        return this.action;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isRestore_update() {
        return this.restore_update;
    }
}
