package lab05.check_act;

public class WorkerA extends Thread{
	
	private Counter counter;
	private int ntimes;
	
	public WorkerA(Counter c, int ntimes){
		this.counter = c;
		this.ntimes = ntimes;
	}
	
	public void run(){
		for (int i = 0; i < this.ntimes; i++){
			if (this.counter.getValue() > 0){
				this.counter.dec();
			}
		}
	}
}
