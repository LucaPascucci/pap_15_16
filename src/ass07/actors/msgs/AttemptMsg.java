package ass07.actors.msgs;

/**
 * Created by Luca on 27/05/16.
 */

/**
 * Rappresenta il tentativo di un player di indovinare il numero
 */
public class AttemptMsg {

    private int number;

    public AttemptMsg(int value){
        this.number = value;
    }

    public int getNumber(){
        return this.number;
    }
}
