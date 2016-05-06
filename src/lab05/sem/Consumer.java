package lab05.sem;

public class Consumer extends Thread{

	private static final int ITERATIONS = 10;	
	private BoundedBuffer<Item> buffer;
	
	public Consumer(BoundedBuffer<Item> b){
		buffer=b;
	}
	
	public void run(){
		try {
			// for(int i=0; i<ITERATIONS; i++){
			while (true){
				Item el = buffer.take();
				consume(el);
			}
		} catch (InterruptedException ex){
			ex.printStackTrace();
		}
	}

	private void consume(Item el) {
		// consume item
		synchronized (System.out){
			System.out.println("consumed "+el.getContent());
		}
	}
	
}
