package lab06.monitors;

public class SynchCell implements ISynchCell{

	private int value;
	private boolean available;

	public SynchCell(){
		available = false;
	}

	@Override
	public synchronized void set(int v){
		value = v;
		available = true;
		notifyAll();  
	}

	@Override
	public synchronized int get() {
		while (!available){
			try {
				wait();
			} catch (InterruptedException ex){}
		}
		return value;
	}
}