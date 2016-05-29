package lab05.sem;

public class Producer extends Thread{

	private static final int ITERATIONS = 10;
	private BoundedBuffer<Item> buffer;
	private int count;
	
	public Producer(BoundedBuffer<Item> b){
		this.buffer = b;
		this.count = 0;
	}
	
	public void run(){
		try {
			// for(int i=0; i<ITERATIONS; i++){
			while (true){
				Item el = produce();
				this.buffer.put(el);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Item produce() {
		return new Item(this.count++);
	}
	
}
