package ass07.actors.msgs;

/**
 * Created by Luca on 27/05/16.
 */

/**
 * Rappresenta il messaggio con il tentativo di un player di indovinare il numero
 */
public class AttemptMsg {

    private int number;
    //Range di generazione di ogni player
    private int max;
    private int min;

    /**
     * Costruttore
     * @param value rappresenta il tentativo (numero)
     * @param max   valore minore del range usato per generare il tentativo
     * @param min   valore minore del range usato per generare il tentativo
     */
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
