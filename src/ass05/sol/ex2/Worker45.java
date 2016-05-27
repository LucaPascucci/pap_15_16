package ass05.sol.ex2;

import java.util.concurrent.Semaphore;

class Worker45 extends Thread {

	private UnsafeCounter c, c4;
	private Semaphore doneInc, mutex, ready;
	
	public Worker45(UnsafeCounter c, UnsafeCounter c4, Semaphore ready, Semaphore mutex, Semaphore doneInc){
		this.c4 = c4;
		this.c = c;
		this.ready = ready;
		this.doneInc = doneInc;
		this.mutex = mutex;
	}
	
	public void run(){
		while (true){
			try {
				ready.acquire();
				System.out.println("c2/3: "+c.getValue());
				mutex.acquire();
				c4.inc();
				mutex.release();
				doneInc.release();
			} catch (Exception ex){
				ex.printStackTrace();
			}			
		}
	}	
}
