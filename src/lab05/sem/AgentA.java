package lab05.sem;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 06/05/16.
 */
public class AgentA extends Thread{

    private Semaphore ev;

    public AgentA(Semaphore ev){
        this.ev = ev;
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.ev.release();

    }
}
