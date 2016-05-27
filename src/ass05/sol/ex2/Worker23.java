package ass05.sol.ex2;

import java.util.concurrent.Semaphore;

class Worker23 extends Thread {

	private UnsafeCounter c;
	private Semaphore doneInc, ready;
	
	public Worker23(UnsafeCounter c, Semaphore ready, Semaphore doneInc){
		this.c = c;
		this.doneInc = doneInc;
		this.ready = ready;
	}
	
	public void run(){
		while (true){
			try {
				ready.acquire();
				c.inc();
				doneInc.release();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			
		}
	}	
}
