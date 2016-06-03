package lab08;

import java.util.Random;

import rx.Observable; 
import rx.Subscriber;
import rx.observables.ConnectableObservable;

public class TestAsyncObsCold {

	public static void main(String[] args) throws Exception {
		
		// creating an asynchronous observable
	
		System.out.println("[MAIN] Creating the cold observable.");

		Observable<Integer> stream = Observable.create((Subscriber<? super Integer> subscriber) -> {
			new Thread(() -> {
				Random gen = new Random(System.currentTimeMillis());
				int i = 0;
				while (true) {
					try {
						int value = gen.nextInt();
						System.out.println("[GEN] " + i + " - value: " + value);
						subscriber.onNext(value);
						Thread.sleep(1000 + gen.nextInt() % 1000);
						i++;
					} catch (Exception ex){}
				}
			}).start();
		});	
		
		Thread.sleep(2500);
		
		System.out.println("[MAIN] Subscribing.");

		stream.subscribe((Integer v) -> {
			System.out.println("[SUB] value observed: " + v);
		});

		/*  note that the lambda expression specified 
			is executed by the thread used to generate 
			the elements of the stream */
		
		// doing some job
		
		while (true) {
			System.out.print(".");
			try {
				Thread.sleep(100);
			} catch (Exception ex){}			
		}
		
	}
}
