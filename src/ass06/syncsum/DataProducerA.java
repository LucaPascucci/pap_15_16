package ass06.syncsum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class DataProducerA extends Thread {

	private SyncAdder adder;
	private IntStream data;
	
	public DataProducerA(SyncAdder adder){
		this.adder = adder;
		data = IntStream.rangeClosed(1,10).map((value) -> value*2);
	}
	
	public void run(){
		data.forEach((value) -> {
			adder.setDataA(value);
		});
	}
	
}
