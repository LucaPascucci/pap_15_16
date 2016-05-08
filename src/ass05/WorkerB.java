package ass05;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public class WorkerB extends Worker {

    //Rappresenta la struttura del Worker 2 e 3

    private Semaphore ev_1;
    private Semaphore ev_4_5;

    public WorkerB (int id, UnsafeCounter counter, Semaphore sem1, Semaphore sem2){
        super(id,counter);
        this.ev_1 = sem1;
        this.ev_4_5 = sem2;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Avviato worker: " + this.id );
        try {
            while (true){
                this.wasteTime(3000);

                this.ev_1.acquire();
                this.counter.inc();
                this.ev_4_5.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
