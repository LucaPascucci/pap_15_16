package ass06.syncsum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class DataProducerB extends Thread {

	private SyncAdder adder;
	private IntStream data;
	
	public DataProducerB(SyncAdder adder){
		this.adder = adder;
		data = IntStream.rangeClosed(1,10).map((value) -> value + 1);
	}
	
	public void run(){
		data.forEach((value) -> {
			adder.setDataB(value);
		});
	}
	
}
