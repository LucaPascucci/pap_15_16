package lab04.test00;

public abstract class Worker extends Thread {
	
	public Worker(String name){
		super(name);
	}

	protected void wasteTime(long ms){
		try {
			sleep(ms);
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	protected void print(String msg){
		synchronized (System.out){
			System.out.print(msg);
		}
	}

	protected void println(String msg){
		synchronized (System.out){
			System.out.println(msg);
		}
	}
}
