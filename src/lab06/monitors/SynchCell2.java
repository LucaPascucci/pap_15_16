package lab06.monitors;

import java.util.concurrent.locks.*;

public class SynchCell2 implements ISynchCell{

	private int value;
	private boolean available;
	private Lock mutex;   
	private Condition isAvail;

	public SynchCell2(){
		available = false;
		mutex = new ReentrantLock(); 
		isAvail = mutex.newCondition();
	}

	@Override
	public void set(int v){
		try {
			mutex.lock();
			value = v;
			available = true;
			isAvail.signalAll();  
		} finally {
			mutex.unlock();
		}
	}

	@Override
	public int get() {
		try {
			mutex.lock();
			if (!available){
				try {
					isAvail.await();
				} catch (InterruptedException ex){}
			} 
			return value;
		} finally {
			mutex.unlock();
		}
	}
}

