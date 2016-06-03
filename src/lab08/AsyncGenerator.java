package lab08;

import java.util.Random;
import rx.Subscriber;

public class AsyncGenerator extends Thread {

	private Subscriber<? super Integer> subscriber;
	private int start;
	private long howLong;
	
	public AsyncGenerator(Subscriber<? super Integer> subscriber, int start, int howLong){
		this.subscriber = subscriber;
		this.start = start;
		this.howLong = howLong;
	}
	
	@Override
	public void run() {
		Random gen = new Random(System.currentTimeMillis());
		int currentValue = start;
		long t0 = System.currentTimeMillis();
		while (true) {
			try {
				subscriber.onNext(currentValue);
				Thread.sleep(1000 + gen.nextInt() % 1000);
				currentValue++;
				if (System.currentTimeMillis() - t0 > howLong){
					break;
				}
			} catch (Exception ex){
				subscriber.onError(ex);
			}
		}
		subscriber.onCompleted();
		
	}
}
