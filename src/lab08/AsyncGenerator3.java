package lab08;

import java.util.Random;
import rx.Subscriber;

public class AsyncGenerator3 extends Thread {

	private Subscriber<? super Integer> subscriber;
	private int seed;
	
	public AsyncGenerator3(Subscriber<? super Integer> subscriber, int seed){
		this.subscriber = subscriber;
		this.seed = seed;
	}
	
	@Override
	public void run() {
		Random gen = new Random(seed);
		for (int i = 0; i < 3; i++){
			int currentValue = gen.nextInt(10);
			System.out.println("GEN "+currentValue);
			subscriber.onNext(currentValue);
			try {
				Thread.sleep(200);
			} catch (Exception ex){
				subscriber.onError(ex);
			}
		}
		subscriber.onCompleted();
		
	}
}
