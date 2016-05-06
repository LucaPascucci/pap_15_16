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
		  } catch (InterruptedException ex){ //capita quando un thread è in attesa su acquire e un'altro thread chiama interrupt su questo thread che si sblocca e genera InterruptedException
			  break;
		  }
		}
	}
	
	protected void action1(){
		println("a1");
		wasteRandomTime(100,500);	
	}
	
	protected void action2(){
		println("a2");
		wasteRandomTime(300,700);	
	}
	protected void action3(){
		println("a3");
		wasteRandomTime(300,700);	
	}
}

