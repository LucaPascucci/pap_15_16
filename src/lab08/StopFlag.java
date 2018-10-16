package lab08;

//Monitor
public class StopFlag {

	private boolean done = false;
	
	public StopFlag(){
		done = false;
	}
	
	public synchronized void setDone(){
		done = true;
	}
	
	public synchronized boolean isDone(){
		return done;
	}
	
}