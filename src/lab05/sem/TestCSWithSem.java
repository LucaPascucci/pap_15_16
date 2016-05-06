package lab05.sem;

import java.util.concurrent.Semaphore;

public class TestCSWithSem {

	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		new MyWorkerB("Eugenio",mutex).start();
		new MyWorkerA("Alda",mutex).start();		
	}

}
