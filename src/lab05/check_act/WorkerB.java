package lab05.check_act;

public class WorkerB extends Thread{
	
	private Counter counter;
	private int ntimes;
	
	public WorkerB(Counter c, int ntimes){
		this.counter = c;
		this.ntimes = ntimes;
	}
	
	public void run(){
		for (int i = 0; i < this.ntimes; i++){
			if (this.counter.getValue() < 1){
				this.counter.inc();
			}
		}
	}
}
