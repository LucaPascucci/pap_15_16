package lab04.test01;

public class MyWorkerA extends Worker {
	
	public MyWorkerA(String name){
		super(name);
	}
	
	public void run(){
		while (true){
		  action1();	
		  action2();	
		}
	}
	
	protected void action1(){
		println("a1");
		wasteRandomTime(10,50);	
	}
	
	protected void action2(){
		println("a2");
		wasteRandomTime(30,70);	
	}
}

