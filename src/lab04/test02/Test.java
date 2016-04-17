package lab04.test02;

public class Test {

	public static void main(String[] args) {
		new MyWorkerB("worker-A").start();
		new MyWorkerA("worker-B").start();		
	}

}
