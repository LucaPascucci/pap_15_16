package lab04.test04;

import java.util.stream.IntStream;

public class TestNotWorking {

	public static void main(String[] args) {
		int howMany = Runtime.getRuntime().availableProcessors();
		long t0 = System.currentTimeMillis();
		IntStream.rangeClosed(0,howMany-1)
			.mapToObj(i -> {
				try {
					return new Thread(() -> {
						System.out.println("Hello from core "+i);
						double waste = 0;
						for (int j = 0; j < 100000; j++){
							for (int k = 0; k < 1000; k++){
								waste = waste + k*Math.sin(j);
							}
						}
					});
				} catch (Exception ex){
					return null;
			}}).peek(t -> {
				t.start(); 
			}).forEach(t -> {
				try {
					t.join();
				} catch (Exception ex){}
			});
		long t1 = System.currentTimeMillis();
		System.out.println("Time elapsed: "+ (t1-t0));
	}

}
