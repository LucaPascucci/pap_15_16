package lab05.sem;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 06/05/16.
 */
public class AgentB extends Thread{

    private Semaphore ev;

    public AgentB(Semaphore ev){
        this.ev = ev;
    }

    @Override
    public void run() {
        super.run();
        try {
            this.ev.acquire();
            for (int i = 0; i < 5; i++) {

                System.out.println("World");
                sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
