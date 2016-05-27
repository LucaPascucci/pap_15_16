package ass05.sol.ex2;

import java.util.concurrent.Semaphore;

public class TestTeam {

	public static void main(String[] args) {

		UnsafeCounter c1 = new UnsafeCounter();
		UnsafeCounter c2 = new UnsafeCounter();
		UnsafeCounter c3 = new UnsafeCounter();
		UnsafeCounter c4 = new UnsafeCounter();
		
		Semaphore mutex = new Semaphore(1);
		
		
		Semaphore doneIncC1a = new Semaphore(0);
		Semaphore doneIncC1b = new Semaphore(0);
		
		Semaphore doneIncC2 = new Semaphore(0);
		Semaphore doneIncC3 = new Semaphore(0);
		
		Semaphore doneIncC4a = new Semaphore(0);
		Semaphore doneIncC4b = new Semaphore(0);

		Semaphore nextTurn = new Semaphore(0);
		
		Worker1 w1 = new Worker1(c1,doneIncC1a,doneIncC1b,nextTurn);
		Worker23 w2 = new Worker23(c2,doneIncC1a,doneIncC2);
		Worker23 w3 = new Worker23(c3,doneIncC1b,doneIncC3);
		Worker45 w4 = new Worker45(c2,c4,doneIncC2,mutex,doneIncC4a);
		Worker45 w5 = new Worker45(c3,c4,doneIncC3,mutex,doneIncC4b);
		Worker6 w6 = new Worker6(c4,doneIncC4a,doneIncC4b,nextTurn);
		
		w1.start();
		w2.start();
		w3.start();
		w4.start();
		w5.start();
		w6.start();
	
	}

}
