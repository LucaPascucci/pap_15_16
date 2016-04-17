package lab04.test00;

public class MyWorkerA extends Worker {
	
	public MyWorkerA(String name){
		super(name);
	}
	
	public void run(){
		action1();	
		action2();	
		action3();	
	}
	
	protected void action1(){
		println("a1");
	}
	
	protected void action2(){
		wasteTime(200); 
		println("a2");
	}

	protected void action3(){
		wasteTime(400);
		println("a3");
	}
	
}

