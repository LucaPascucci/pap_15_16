package lab05.sem;

import java.util.concurrent.Semaphore;

public class MyWorkerA extends Worker {
	
	private Semaphore mutex;
	
	public MyWorkerA(String name, Semaphore mutex){
		super(name);
		this.mutex = mutex;
	}
	
	public void run(){
		while (true){
		  action1();	
		  try {
			  mutex.acquire();
			  action2();	
			  action3();	
			  mutex.release();
		  } catch (InterruptedException ex){
			  break;
		  }
		}
	}
	
	protected void action1(){
		println("aa");
		wasteRandomTime(100,500);	
	}
	
	protected void action2(){
		println("bb");
		wasteRandomTime(300,700);	
	}
	protected void action3(){
		println("cc");
		wasteRandomTime(300,700);	
	}
}

