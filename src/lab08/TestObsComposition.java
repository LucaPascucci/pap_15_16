package lab08;

import rx.Observable;
import rx.Subscriber;

public class TestObsComposition {

	public static void main(String[] args) {

		System.out.println("Creating the observables");

		// simple composition
		Observable<Integer> s1 = Observable.create((Subscriber<? super Integer> subscriber) -> {
			new AsyncGenerator(subscriber,0,5000).start();
		});	

		Observable<Integer> s2 = Observable.create((Subscriber<? super Integer> subscriber) -> {
			new AsyncGenerator(subscriber,100,6000).start();
		});	

		System.out.println("Creating a merged stream");

		Observable<String> newStream =
			Observable.merge(s1,s2)
				.map((Integer x) -> "merged stream: " + x);
		
		System.out.println("Creating a merged and reduced stream");
		
		Observable<Integer> newStream2 =
				Observable.merge(s1,s2)
					.reduce((a,b) -> a + b);

		System.out.println("Subscribing");

		newStream.subscribe((String s) -> {
			System.out.println(s);
		});

		newStream2.subscribe((Integer v) -> {
			System.out.println("Final sum: " + v);
		});


	}

}
