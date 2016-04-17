package lab04.test03;

public class MyWorkerA extends Worker {
	
	public MyWorkerA(String name){
		super(name);
	}
	
	public void run(){
		println("a1");
		wasteTime(5000);
		println("a2");
	}
	
}

