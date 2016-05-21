package lab06.syncsum;

public class DataConsumer extends Thread {

	private SyncAdder adder;
	
	public DataConsumer(SyncAdder adder){
		this.adder = adder;
	}
	
	public void run(){
		while (true){
			int value = adder.getSum();
			System.out.println(value);
		}
	}
	
}
