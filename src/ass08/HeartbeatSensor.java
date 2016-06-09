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
		this.gen = new Random(System.nanoTime());
		this.targetValues = new HeartbeatSlot[5];
		this.targetValues[0] = new HeartbeatSlot(0,3,70,90);
		this.targetValues[1] = new HeartbeatSlot(3,5,90,110);
		this.targetValues[2] = new HeartbeatSlot(5,8,110,110);
		this.targetValues[3] = new HeartbeatSlot(8,10,110,80);
		this.targetValues[4] = new HeartbeatSlot(10,12,80,70);
		this.period = 12;
		this.currentValue = 70;

		this.exec = Executors.newScheduledThreadPool(1);
		this.updateTask = new UpdateTask();
		
		this.startTime = System.currentTimeMillis();
		this.exec.scheduleAtFixedRate(this.updateTask, 0, 100, java.util.concurrent.TimeUnit.MILLISECONDS);
	}

	
	/**
	 * Reading the current sensor value 
	 * 
	 * @return sensor value
	 */
	public int getCurrentValue() {
		synchronized (this.updateTask){
			return (int) this.currentValue;
		}
	}

	private HeartbeatSlot findSlot(double dt){
		for (HeartbeatSlot s: this.targetValues){
			if (dt >= s.getFromTime() && dt < s.getToTime()){
				return s;
			}
		}
		return null;
	}
	
	class UpdateTask implements Runnable {
		public void run(){
			
			/* dt, in seconds */
			double currentTime = 0.001*(System.currentTimeMillis() - startTime);

			/* normalized */
			double t = currentTime - Math.floor(currentTime / period) * period;
					
			HeartbeatSlot slot = findSlot(t);			
			double alfa = (t - slot.getFromTime()) / (slot.getToTime() - slot.getFromTime());
			double variab = -1 + gen.nextInt(2);
			double newValue = slot.getFromValue()+(slot.getToValue() - currentValue) * alfa  + variab;
			
			synchronized (this){
				currentValue = newValue;
			}
		}
	}
		
}


