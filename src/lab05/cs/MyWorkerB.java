package lab05.cs;

public class MyWorkerB extends Worker {
	
	private Object lock;
	
	public MyWorkerB(String name, Object lock){
		super(name);
		this.lock = lock;
	}

	public void run(){
		while (true){
		  synchronized(lock){
			  this.action1();
			  this.action2();
		  }
		  this.action3();
		}
	}
	
	protected void action1(){
		println("b1");
		this.wasteRandomTime(0,1000);
	}
	
	protected void action2(){
		println("b2");
		this.wasteRandomTime(100,200);
	}
	protected void action3(){
		println("b3");
		this.wasteRandomTime(1000,2000);
	}
}
