package ass05.sol.ex2;

import java.util.concurrent.Semaphore;

public class Worker6 extends Thread {

	private UnsafeCounter c;
	private Semaphore doneIncA, doneIncB, nextTurn;
	
	public Worker6(UnsafeCounter c, Semaphore doneIncA, Semaphore doneIncB, Semaphore nextTurn){
		this.c = c;
		this.doneIncA = doneIncA;
		this.doneIncB = doneIncB;
		this.nextTurn = nextTurn;
	}
	
	public void run(){
		while (true){
			try {
				doneIncA.acquire();
				doneIncB.acquire();
				System.out.println("c4: "+c.getValue());
				//sleep(5000);
				nextTurn.release();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			
		}
	}	
}

