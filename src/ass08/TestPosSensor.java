package ass08;

import pap.ass08.pos.*;

/**
 * Testing the position sensor
 * 
 * @author aricci
 *
 */
public class TestPosSensor {

	public static void main(String[] args) {
		
		PosSensor sensor = new PosSensor();
		
		/*
		 * Reading the sensor every 100 ms for 5 seconds
		 */
		long t0 = System.currentTimeMillis();
		long dt = 0;
		while (dt < 15000){
			System.out.println("dt: " + dt +" - value: " + sensor.getCurrentValue());
			try {
				Thread.sleep(100);
			} catch (Exception ex){}
			dt = System.currentTimeMillis() - t0;
		}
		
		System.exit(0);
	}

}
