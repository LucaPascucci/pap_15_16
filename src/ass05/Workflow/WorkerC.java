package ass05.Workflow;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public class WorkerC extends Worker {

    //Rappresenta la struttura del Worker 4 e 5

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
                this.wasteTime();

                this.ev_2_3.acquire();  //aspettano che worker-2 e worker-3 abbiano rispettivamente incrementato i rispettivi contatori
                this.println("Worker " + this.id + " -> Counter " + this.counter.getId() + " Value = " + this.counter.getValue());

                //Mutua esclusione per incrementare il contatore-4 che worker-4 e worker-5 hanno in condivisa
                this.mutex.acquire();
                this.shared_counter.inc();
                this.mutex.release();

                this.ev_6.release(); //avvisano worker-6 di aver incrementato il contatore-4 (condiviso)
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
