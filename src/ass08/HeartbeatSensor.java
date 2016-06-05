package ass08;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Class implementing a simulated heart beat sensor
 * (Assignment #08)
 * 
 * @author aricci
 *
 */
public class HeartbeatSensor {	
	private Random gen;
	private UpdateTask updateTask;
	private ScheduledExecutorService exec;
	
	/* simulated target values */
	private HeartbeatSlot targetValues[];
	
	private double currentValue;
	private double startTime;
	private double period;
	
	/**
	 * Creates a simulated heartbeat sensor 
	 * 
	 */
	public HeartbeatSensor(){
		gen = new Random(System.nanoTime());
		targetValues = new HeartbeatSlot[5];
		targetValues[0] = new HeartbeatSlot(0,3,70,90);
		targetValues[1] = new HeartbeatSlot(3,5,90,110);
		targetValues[2] = new HeartbeatSlot(5,8,110,110);
		targetValues[3] = new HeartbeatSlot(8,10,110,80);
		targetValues[4] = new HeartbeatSlot(10,12,80,70);
		period = 12;
		currentValue = 70;		

		exec = Executors.newScheduledThreadPool(1);
		updateTask = new UpdateTask();
		
		startTime = System.currentTimeMillis();
		exec.scheduleAtFixedRate(updateTask, 0, 100, java.util.concurrent.TimeUnit.MILLISECONDS);
	}

	
	/**
	 * Reading the current sensor value 
	 * 
	 * @return sensor value
	 */
	public int getCurrentValue() {
		synchronized (updateTask){
			return (int) currentValue;
		}
	}

	private HeartbeatSlot findSlot(double dt){
		for (HeartbeatSlot s: targetValues){
			if (dt >= s.getFromTime() && dt < s.getToTime()){
				return s;
			}
		}
		return null;
	}
	
	class UpdateTask implements Runnable {
		public void run(){
			
			/* dt, in seconds */
			double currentTime = 0.001*(System.currentTimeMillis()-startTime);

			/* normalized */
			double t = currentTime - Math.floor(currentTime/period)*period;
					
			HeartbeatSlot slot = findSlot(t);			
			double alfa = (t - slot.getFromTime()) / (slot.getToTime() - slot.getFromTime());
			double variab = -1 + gen.nextInt(2);
			double newValue = slot.getFromValue()+(slot.getToValue() - currentValue)*alfa  + variab;
			
			synchronized (this){
				currentValue = newValue;
			}
		}
	}
		
}


