package lab05.lost_updates;

/**
 * Created by Luca on 06/05/16.
 */
public class SafeCounter implements Counter{

    private int cont;

    public SafeCounter(int base){
        this.cont = base;
    }

    public synchronized void inc(){
        this.cont++;
    }

    public synchronized int getValue(){
        return this.cont;
    }
}
