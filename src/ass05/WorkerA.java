package ass05;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public class WorkerA extends Worker {

    //Rappresenta la struttura del Worker 1

    private Semaphore ev_2;
    private Semaphore ev_3;
    private Semaphore ev_6;

    public WorkerA (int id, UnsafeCounter counter, Semaphore sem1, Semaphore sem2, Semaphore sem3){
        super(id,counter);
        this.ev_2 = sem1;
        this.ev_3 = sem2;
        this.ev_6 = sem3;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Avviato worker: " + this.id);
        try {
            while (true){
                this.counter.inc();
                this.ev_2.release();
                this.ev_3.release();
                this.ev_6.acquire();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}