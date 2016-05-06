package lab05.sem;

import java.util.concurrent.Semaphore;

public class MyWorkerB extends Worker {

	private Semaphore mutex;

	public MyWorkerB(String name, Semaphore mutex){
		super(name);
		this.mutex = mutex;
	}

	public void run(){
		while (true){
			try {
				this.mutex.acquire();
				this.action1();
				this.action2();
				this.mutex.release();
			} catch (InterruptedException ex){
				break;
			}
			this.action3();
		}
	}

	protected void action1(){
		println("b1");
		wasteRandomTime(0,1000);
	}

	protected void action2(){
		println("b2");
		wasteRandomTime(100,200);
	}
	protected void action3(){
		println("b3");
		wasteRandomTime(1000,2000);
	}
}
