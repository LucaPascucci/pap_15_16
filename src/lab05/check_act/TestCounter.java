package lab05.check_act;

/**
 * To enable assertions: run with -ea option
 * 
 * @author aricci
 *
 */
public class TestCounter {

	public static void main(String[] args) throws Exception {
		int ntimes = 1000000000;
		Counter c = new Counter(0,1);
		WorkerA w1 = new WorkerA(c,ntimes);
		WorkerB w2 = new WorkerB(c,ntimes);
		WorkerB w3 = new WorkerB(c,ntimes);
		w1.start();
		w2.start();
		w3.start();
		w1.join();
		w2.join();
		w3.join();
		System.out.println("Counter final value: "+c.getValue());
	}
}
