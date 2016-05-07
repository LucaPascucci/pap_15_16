package ass05;

/**
 * Created by Luca on 07/05/16.
 */
public class UnsafeCounter {

    private int id;
    private int cont;

    public UnsafeCounter(int id, int base){
        this.id = id;
        this.cont = base;
    }

    public void inc(){
        this.cont++;
    }

    public int getValue(){
        return this.cont;
    }

    public int getId(){
        return this.id;
    }
}