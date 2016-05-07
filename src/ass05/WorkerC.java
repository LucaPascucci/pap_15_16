package ass05;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public class WorkerC extends Worker {

    private Semaphore ev_2_3;
    private Semaphore ev_6;
    private Semaphore mutex;
    private UnsafeCounter shared_counter;

    public WorkerC (int id, UnsafeCounter counter, UnsafeCounter shared_counter, Semaphore sem1, Semaphore sem2, Semaphore mutex){
        super(id,counter);
        this.shared_counter = shared_counter;
        this.ev_2_3 = sem1;
        this.ev_6 = sem2;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Avviato worker: " + this.id);
        try {
            while (true){
                this.ev_2_3.acquire();
                this.println("Worker " + this.id + " -> Counter " + this.counter.getId() + " Value = " + this.counter.getValue());

                //Mutua esclusione
                this.mutex.acquire();
                this.shared_counter.inc();
                this.mutex.release();

                this.ev_6.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
