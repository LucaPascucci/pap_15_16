package ass07.actors.msgs;

/**
 * Created by Luca on 27/05/16.
 */

/**
 * Messaggio mandato dall'oracolo per suggerire al Player se il numero da indovinare è maggiore o minore al tentativo effettuato
 */
public class HintMsg {

    /**
     *  1 = valore più grande
     * -1 = valore più piccolo
     */
    private int hint;

    public HintMsg(int value){
        this.hint = value;
    }

    public int getHint(){
        return this.hint;
    }
}
