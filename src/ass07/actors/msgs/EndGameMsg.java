package ass07.actors.msgs;

/**
 * Created by Luca on 27/05/16.
 */

/**
 * rappresenta il messaggio inviato dall'oracolo per decretare il vincitore
 */
public class EndGameMsg {

    /**
     *  true per il player vincitore
     *  false per il player perdente
     */
    private boolean result;

    public EndGameMsg(boolean value){
        this.result = value;
    }

    public boolean getResult(){
        return this.result;
    }
}
