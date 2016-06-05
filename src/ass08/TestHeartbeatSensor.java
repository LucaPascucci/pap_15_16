package ass08;

/**
 * Testing the heart beat  sensor
 * 
 * @author aricci
 *
 */
public class TestHeartbeatSensor {

	public static void main(String[] args) {
		
		HeartbeatSensor sensor = new HeartbeatSensor();
		
		/*
		 * Reading the sensor every 100 ms for 5 seconds
		 */
		long t0 = System.currentTimeMillis();
		long dt = 0;
		while (dt < 15000){
			System.out.println("dt: " + dt + " - value: " + sensor.getCurrentValue());
			try {
				Thread.sleep(100);
			} catch (Exception ex){}
			dt = System.currentTimeMillis() - t0;
		}
		
		System.exit(0);
	}

}
