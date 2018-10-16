package lab08;

import java.util.Random;

import rx.Observable; 
import rx.Subscriber;
import rx.Subscription;
import rx.observables.ConnectableObservable;

public class TestAsyncObsWithUnsub {

	public static void main(String[] args){
		
		// creating an asynchronous observable
	
		Observable<Integer> stream = Observable.create((Subscriber<? super Integer> subscriber) -> {
			new Thread(() -> {
				Random gen = new Random(System.currentTimeMillis());
				while (true) {
					try {
						int n = gen.nextInt();
						subscriber.onNext(n);
						System.out.println("[GEN-1]: " + n);
						Thread.sleep(1000 + gen.nextInt() % 1000);
					} catch (Exception ex){}
				}
			}).start();
			
			Random gen = new Random(System.currentTimeMillis());
			while (true) {
				try {
					gen.nextInt();
					int n = gen.nextInt();
					subscriber.onNext(n);
					System.out.println("[GEN-2]: " + n);
					Thread.sleep(1000 + gen.nextInt() % 1000);
				} catch (Exception ex){}
			}
			
		});	
		
		
		Subscription sub = stream.subscribe((Integer v) -> {
			System.out.println("value: " + v);
		});
		
		// doing some job 
		try {
			Thread.sleep(1000);
		} catch (Exception ex){}			
		
		System.out.println("unsubscribing... ");
		sub.unsubscribe();

		// doing some job 
		try {
			Thread.sleep(2000);
		} catch (Exception ex){}			
		
		System.out.println("done.");

	}
}
