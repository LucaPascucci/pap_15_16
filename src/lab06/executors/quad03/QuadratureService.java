package lab06.executors.quad03;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class QuadratureService extends Thread {

	private int numTasks;
	private ExecutorService executor;
	
	public QuadratureService (int numTasks, int poolSize){		
		this.numTasks = numTasks;
		this.executor = Executors.newFixedThreadPool(poolSize);
	}
	
	public double compute(IFunction mf, double a, double b) throws InterruptedException { 
		long startTime = System.currentTimeMillis();
		double x0 = a;
		double step = (b-a)/ this.numTasks;
	    Set<Future<Double>> resultSet = new HashSet<>();
		for (int i = 0; i < this.numTasks; i++) {
			try {
				Future<Double> res = this.executor.submit(new ComputeAreaTask(i,x0, x0 + step, mf));
				resultSet.add(res);
				log("submitted task-" + i + " : " + x0 + " " + (x0 + step));
				x0 += step;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}				

	    double sum = 0;
	    for (Future<Double> future : resultSet) {
	    	try {
	    		sum += future.get();
	    	} catch (Exception ex){
	    		ex.printStackTrace();
	    	}
	    }
		this.log("Execution Time: " + (System.currentTimeMillis() - startTime));
	    this.executor.shutdown();
		return sum;
	}
	
	private void log(String msg){
		System.out.println("[SERVICE] "+msg);
	}
}
