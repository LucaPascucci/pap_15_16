package lab08;

import rx.Observable;
import rx.Subscriber;

public class HelloRx {

	public static void main(String[] args){
				
		System.out.println("Simple subscription to a sync source...");

		// synch data source
		
		String[] words = { "Hello", " ", "World", "!\n" }; 
		
		// simple subscription 
		//specificando gli item con from
        //sincrono un solo flusso quindi se uno si blocca si bloccano anche i successivi
        //sottoscrizione che dice cosa ne devi fare del valore che viene reso disponibile dall'observable
		Observable
			.from(words)
			.subscribe((String s) -> {
				System.out.print(s);
			});

		// full subscription: onNext(), onError(), onCompleted()

		System.out.println("Full subscription...");

        //crea in modo sincrono tutti i token assegnandoli modalità di
		Observable
			.from(words)
			.subscribe(
				(String s) -> {
					System.out.println("> " + s);
					try { 
						Thread.sleep(1000); 
					} catch (Exception ex){
                        //vuoto
                    }
				},
	            (Throwable t) -> System.out.println("error  " + t),
	            () -> System.out.println("completed")
	        );

        System.out.println("here.");

	}
}
