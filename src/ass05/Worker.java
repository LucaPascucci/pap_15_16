package ass05;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public abstract class Worker extends Thread{

    protected int id;
    protected UnsafeCounter counter;

    public Worker(int id, UnsafeCounter counter){
        this.id = id;
        this.counter = counter;

    }

    protected void wasteTime(long ms){
        try {
            sleep(ms);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    protected synchronized void println(String msg){
        System.out.println(msg);
    }

}
