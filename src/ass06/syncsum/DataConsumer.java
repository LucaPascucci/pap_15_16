package ass06.syncsum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

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
