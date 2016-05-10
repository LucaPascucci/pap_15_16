package ass05.Workflow;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 07/05/16.
 */
public class Workflow {

    public static void main (String args[]){
        UnsafeCounter c1 = new UnsafeCounter(1,0);
        UnsafeCounter c2 = new UnsafeCounter(2,0);
        UnsafeCounter c3 = new UnsafeCounter(3,0);
        UnsafeCounter c4 = new UnsafeCounter(4,0);

        Semaphore ev_1_2 = new Semaphore(0);
        Semaphore ev_1_3 = new Semaphore(0);
        Semaphore ev_2_4 = new Semaphore(0);
        Semaphore ev_3_5 = new Semaphore(0);
        Semaphore ev_4_5_6 = new Semaphore(0);
        Semaphore ev_6_1 = new Semaphore(0);
        Semaphore mutex_4_5 = new Semaphore(1); //inzializzato ad 1 per gestire la mutua esclusione

        WorkerA worker1 = new WorkerA(1,c1,ev_1_2,ev_1_3,ev_6_1);
        WorkerB worker2 = new WorkerB(2,c2,ev_1_2,ev_2_4);
        WorkerB worker3 = new WorkerB(3,c3,ev_1_3,ev_3_5);
        WorkerC worker4 = new WorkerC(4,c2,c4,ev_2_4,ev_4_5_6,mutex_4_5);
        WorkerC worker5 = new WorkerC(5,c3,c4,ev_3_5,ev_4_5_6,mutex_4_5);
        WorkerD worker6 = new WorkerD(6,c4,ev_4_5_6,ev_6_1);

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();
        worker5.start();
        worker6.start();
    }
}
