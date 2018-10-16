package ass05.Workflow;

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
                this.wasteTime();

                this.counter.inc();
                this.ev_2.release();    // comunica a worker-2 che ha incrementato il contatore c1
                this.ev_3.release();    // comunica a worker-3 che ha incrementato il contatore c1
                this.ev_6.acquire();    // attende che worker-6 abbia stampato il contatore c4
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}