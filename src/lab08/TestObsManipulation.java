package lab08;

import rx.Observable;
import rx.Subscriber;

public class TestObsManipulation {

	public static void main(String[] args) {
		
		System.out.println("Creating the observable");
		
		Observable<Integer> stream = Observable.create((Subscriber<? super Integer> subscriber) -> {
			new AsyncGenerator(subscriber,400,10000).start();
		});	
		
		System.out.println("Creating the new stream.");
		
		Observable<String> newStream =
			stream
			.skip(5)
			.take(20)
			.map(v -> "value: "+v);

		System.out.println("Subscribing.");
		
		newStream.subscribe((String s) -> {
			System.out.println(s);
		});
	}

}
