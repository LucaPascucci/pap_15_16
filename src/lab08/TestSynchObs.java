package lab08;

import java.util.stream.IntStream;
import rx.Observable; 
import rx.Subscriber;

public class TestSynchObs {

	public static void main(String[] args){
		
		System.out.println("Creating the Observable");

		Observable<Integer> stream = Observable.create((Subscriber<? super Integer> observer) -> {
			IntStream.range(0, 10).forEach(value -> {
				System.out.println("[OBS] Gen: " + value);
				observer.onNext(value); //da in pasto il valore al subscriber
			});
		});	
		
		System.out.println("Subscribing.");

        //solo quando faccio la sottoscrizione viene avviata la generatore
		stream.subscribe((Integer v) -> {
			System.out.println("[SUB] value: " + v);
		});
		
	}
}
