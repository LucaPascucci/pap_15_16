package ass07.actors.msgs;

/**
 * Created by Luca on 27/05/16.
 */

/**
 * Rappresenta il tentativo di un player di indovinare il numero
 */
public class AttemptMsg {

    private int number;

    //Intervallo di generazione di ogni player
    private int max;
    private int min;

    public AttemptMsg(int value, int max, int min){
        this.number = value;
        this.max = max;
        this.min = min;
    }

    public int getNumber(){
        return this.number;
    }

    public int getMax() {
        return this.max;
    }

    public int getMin(){
        return this.min;
    }
}
