package lab08;

import java.util.Random;
import rx.Subscriber;

public class AsyncGenerator4 extends Thread {

	private Subscriber<? super Integer> subscriber;
	
	public AsyncGenerator4(Subscriber<? super Integer> subscriber){
		this.subscriber = subscriber;
	}
	
	@Override
	public void run() {
		System.out.println("RUNNING GEN");
		Random gen = new Random(System.nanoTime());
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
