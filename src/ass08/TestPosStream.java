package ass08;

import pap.ass08.pos.*;
import rx.Observable;

/**
 * Testing the position observable
 * 
 * @author aricci
 *
 */
public class TestPosStream {

	public static void main(String[] args) {
		
		System.out.println("Creating the sensor.");
		PosSensor sensor = new PosSensor();
		Flag flag = new Flag();

		System.out.println("Creating the stream.");
		
		/* making a stream generating a value every 100 ms */
		
		Observable<P2d> stream = sensor.makeObservable(100, flag);

		System.out.println("Observing the stream for 2 secs.");

		/* observing the stream for 2000 ms */
		
		long t0 = System.currentTimeMillis(); 
		stream.subscribe((p) -> {
			System.out.println("> "+p);
			if (System.currentTimeMillis() - t0 > 2000){
				flag.set();
			}
		});
		
		try {
			flag.waitSet();
		} catch (InterruptedException ex){}

		System.out.println("Done.");
		System.exit(0);
	}

}
