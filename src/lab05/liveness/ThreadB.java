package lab05.liveness;

import java.util.Random;

public class ThreadB extends Thread {
 
	private LeftRightDeadlock res;
	
	public ThreadB(LeftRightDeadlock res){
		this.res = res;
	}
	
	public void run(){
		Random gen = new Random();
		while (true){
			try {
				Thread.sleep(gen.nextInt(10));
			} catch (Exception ex){}
			res.leftRight();
		}
	}

}
