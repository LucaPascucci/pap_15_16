package test.gof2;

public class Flag {

	private boolean stop=false;
	
	public synchronized void set(boolean flag){
		stop=flag;
	}
	
	public synchronized boolean getState(){
		return stop;
	}
}
