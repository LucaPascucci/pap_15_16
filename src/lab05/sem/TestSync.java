package lab05.sem;

import java.util.concurrent.Semaphore;

/**
 * Created by Luca on 06/05/16.
 */
public class TestSync {

    public static void main(String args[]){
        Semaphore ev = new Semaphore(0);
        new AgentA(ev).start();
        new AgentB(ev).start();
    }
}
