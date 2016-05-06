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
				  mutex.acquire();
				  action1();	
				  action2();
				  mutex.release();
			  } catch (InterruptedException ex){
				  break;
			  }

			 action3();
		}
	}
	
	protected void action1(){
		println("ccc");
		wasteRandomTime(0,1000);	
	}
	
	protected void action2(){
		println("ddd");
		wasteRandomTime(100,200);	
	}
	protected void action3(){
		println("eee");
		wasteRandomTime(1000,2000);	
	}
}
