package ass05.sol.ex2;

import java.util.concurrent.Semaphore;

public class Worker1 extends Thread {

	private UnsafeCounter c1;
	private Semaphore doneIncC1a, doneIncC1b, ready;
	
	public Worker1(UnsafeCounter c1, Semaphore doneIncC1a, Semaphore doneIncC1b, Semaphore ready){
		this.c1 = c1;
		this.doneIncC1a = doneIncC1a;
		this.doneIncC1b = doneIncC1b;
		this.ready = ready;
	}
	
	public void run(){
		while (true){
			c1.inc();
			doneIncC1a.release();
			doneIncC1b.release();
			try {
				ready.acquire();
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
	}	
}