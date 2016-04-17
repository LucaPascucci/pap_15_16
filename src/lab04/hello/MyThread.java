package lab04.hello;

public class MyThread extends Thread {

	public MyThread(String myName){
		super(myName);
	}
	
	public void run(){
		System.out.println("Hello concurrent world!");
	}
}
