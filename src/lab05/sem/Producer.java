package lab05.sem;

import java.util.concurrent.Semaphore;


public class Producer extends Thread{

	private static final int ITERATIONS = 10;
	private BoundedBuffer<Item> buffer;
	private int count;
	
	public Producer(BoundedBuffer<Item> b){
		buffer=b;
		count = 0;
	}
	
	public void run(){
		try {
			// for(int i=0; i<ITERATIONS; i++){
			while (true){
				Item el = produce();
				buffer.put(el);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Item produce() {
		return new Item(count++);
	}
	
}
