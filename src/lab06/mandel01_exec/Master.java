package lab06.mandel01_exec;

import java.util.concurrent.*;

/**
 * Master controller agent 
 * 
 * @author aricci
 *
 */
public class Master extends Thread {

	private MandelbrotView view;
	private MandelbrotSet set;
	private Complex c0;
	private double diam;
	private Flag stopFlag;
	
	public Master(Complex c0, double diam, MandelbrotSet set, MandelbrotView view, Flag stopFlag){
		this.set = set;
		this.view = view;
		this.c0 = c0;
		this.diam = diam;
		this.stopFlag = stopFlag;
	}
	
	public void run(){
		try {
			this.view.changeState("Processing...");
			long t0 = System.currentTimeMillis();
			int nThreads = Runtime.getRuntime().availableProcessors() + 1;
			ExecutorService executor = Executors.newFixedThreadPool(nThreads);
			
			int nTasks = 100;
			int w = this.set.getSizeX();
	       	int x0 = 0;
	    	int dx = w / nTasks;       
	    	
	    	for (int i = 0; i < nTasks-1; i++){
				executor.execute(new ComputeStripeTask(x0, x0 + dx, this.c0, diam, set, stopFlag));
	    		x0 += dx;
	    	}
	    	executor.execute(new ComputeStripeTask(x0, w, c0, diam, set, stopFlag));
    		
	    	executor.shutdown();
	    	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	    	this.view.setUpdated(set);
	    	
	    	if (!this.stopFlag.isSet()){
	    		long t1 = System.currentTimeMillis();
	    		this.view.changeState("completed - time elapsed: "+(t1-t0));
	    	} else {
	    		view.changeState("interrupted");
	    	}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void log(String msg){
		synchronized(System.out){
			System.out.println(msg);
		}
	}
	
}
