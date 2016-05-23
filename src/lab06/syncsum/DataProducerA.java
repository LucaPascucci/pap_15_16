package lab06.syncsum;

import java.util.stream.*;

public class DataProducerA extends Thread {

	private SyncAdder adder;
	private IntStream data;
	
	public DataProducerA(SyncAdder adder){
		this.adder = adder;
		this.data = IntStream.rangeClosed(1,10).map((value) -> value*2);
	}
	
	public void run(){
		this.data.forEach((value) -> {
			this.adder.setDataA(value);
		});
	}
	
}
