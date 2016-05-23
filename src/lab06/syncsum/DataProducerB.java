package lab06.syncsum;

import java.util.stream.*;

public class DataProducerB extends Thread {

	private SyncAdder adder;
	private IntStream data;
	
	public DataProducerB(SyncAdder adder){
		this.adder = adder;
		this.data = IntStream.rangeClosed(1,10).map((value) -> value + 1);
	}
	
	public void run(){
		this.data.forEach((value) -> {
			this.adder.setDataB(value);
		});
	}
	
}
