package lab04.test00;


public class MyWorkerB extends Worker {
	
	public MyWorkerB(String name){
		super(name);
	}

	public void run(){
		action1();	
		action2();
	}
	
	protected void action1(){
		wasteTime(100); 
		println("b1");
	}
	
	protected void action2(){
  	    wasteTime(300); 
		println("b2");
	}
}
