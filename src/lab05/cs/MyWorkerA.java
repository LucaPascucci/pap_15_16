package lab05.cs;

public class MyWorkerA extends Worker {
	
	private Object lock;
	
	public MyWorkerA(String name, Object lock){
		super(name);
		this.lock = lock;
	}
	
	public void run(){
		while (true){
		  action1();	
		  synchronized(lock){
			  action2();	
			  action3();	
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

