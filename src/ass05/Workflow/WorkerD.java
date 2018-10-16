package ass05.Workflow;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public class WorkerD extends Worker {

    //Rappresenta la struttura del Worker 6

    private Semaphore ev_4_5;
    private Semaphore ev_1;

    public WorkerD (int id, UnsafeCounter counter, Semaphore sem1, Semaphore sem2){
        super(id,counter);
        this.ev_4_5 = sem1;
        this.ev_1 = sem2;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Avviato worker: " + this.id);
        try {
            while (true){
                this.wasteTime();

                this.ev_4_5.acquire(2); //aspetta che worker-4 e worker-5 abbiano entrambi incrementato c4
                this.println("Worker " + this.id + " -> Counter " + this.counter.getId() + " Value = " + this.counter.getValue() + '\n');
                this.ev_1.release();    //avvisa worker-1 di aver stampato c4
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
