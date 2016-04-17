package lab04.test03;

public class MyWorkerB extends Worker {
	
	private MyWorkerA friend;
	
	public MyWorkerB(String name, MyWorkerA t){
		super(name);
		this.friend = t;
	}

	public void run(){
		println("b1");
		println("b2");
		try {
			this.friend.join();
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
		println("b3");
	}
}
