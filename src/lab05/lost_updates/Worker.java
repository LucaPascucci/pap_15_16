package lab05.lost_updates;

public class Worker extends Thread{
	
	private Counter counter;
	private int ntimes;
	
	public Worker(UnsafeCounter c, int ntimes){
		this.counter = c;
		this.ntimes = ntimes;
	}

	public Worker(SafeCounter c, int ntimes){
		this.counter = c;
		this.ntimes = ntimes;
	}
	
	public void run(){
		for (int i = 0; i < this.ntimes; i++){
			this.counter.inc();
		}
	}
}
