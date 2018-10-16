package lab08;

import java.util.Random;
import rx.Observable; 
import rx.Subscriber;

public class TestAsyncObsWithCompletion {

	public static void main(String[] args){
		
		StopFlag flag = new StopFlag();
		
		// creating an asynchronous observable
	
		System.out.println("Creating the observable.");

		Observable<Integer> stream = Observable.create((Subscriber<? super Integer> subscriber) -> {
			new Thread(() -> {
				Random gen = new Random(System.currentTimeMillis());
				
				long t0 = System.currentTimeMillis();
				while (true) {
					try {
						subscriber.onNext(gen.nextInt());
						Thread.sleep(1000 + gen.nextInt() % 1000);
						if (System.currentTimeMillis() - t0 > 5000){
							break;
						}
					} catch (Exception ex){
					}
				};				
				// notify completion after 5 secs
				subscriber.onCompleted();
			}).start();
		});	
		
		System.out.println("Subscribing.");

		stream.subscribe(
			(Integer v) -> {
				System.out.println("value: " + v);
			},
			(Throwable t) -> {},
			() -> {
				System.out.println("done.");
				flag.setDone();
			});
		
		// doing some job 
		
		while (!flag.isDone()) {
			System.out.print(".");
			try {
				Thread.sleep(100);
			} catch (Exception ex){}			
		}
		
	}
}
