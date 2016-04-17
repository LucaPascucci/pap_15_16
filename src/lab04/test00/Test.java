package lab04.test00;

/*
 * Non determinism.
 * 
 */
public class Test {

	public static void main(String[] args) {
		new MyWorkerA("worker-A").start();		
		new MyWorkerB("worker-B").start();
	}

}
