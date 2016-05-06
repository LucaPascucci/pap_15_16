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
			  action1();	
			  action2();
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
