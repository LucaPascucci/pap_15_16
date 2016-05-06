package lab05.cs;

public class MyWorkerA extends Worker {
	
	private Object lock;
	
	public MyWorkerA(String name, Object lock){
		super(name);
		this.lock = lock;
	}
	
	public void run(){
		while (true){
		  this.action1();
		  synchronized(lock){ //tutto ciò che è sync non verrà interrotta da altre parti di codice in sync
			  this.action2();
			  this.action3();
		  }
		}
	}
	
	protected void action1(){
		this.println("a1");
		this.wasteRandomTime(100,500);
	}
	
	protected void action2(){
		this.println("a2");
		this.wasteRandomTime(300,700);
	}
	protected void action3(){
		this.println("a3");
		this.wasteRandomTime(300,700);
	}
}

