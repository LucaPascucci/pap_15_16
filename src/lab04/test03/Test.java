package lab04.test03;

/**
 * Test join.
 * 
 * @author aricci
 *
 */
public class Test {

	public static void main(String[] args) {
		MyWorkerA t = new MyWorkerA("worker-A");
		t.start();
		new MyWorkerB("worker-B", t).start();
	}

}
