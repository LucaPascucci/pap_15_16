package lab06.executors.quad02;

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
		QuadratureResult result = new QuadratureResult();		
		double x0 = a;
		double step = (b-a) / this.numTasks;
		for (int i = 0; i < this.numTasks; i++) {
			try {
				this.executor.execute(new ComputeAreaTask(i,x0, x0 + step, mf, result));
                log("submitted task-" + i + " : " + x0 + " " + (x0 + step));
				x0 += step;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}				

		//utilizzo i metodi dell'executor per aspettare i task
		this.executor.shutdown();
		this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        double res = result.getResult();
        this.log("Execution Time: " + (System.currentTimeMillis() - startTime));
		return res;
	}
	
	
	private void log(String msg){
		System.out.println("[SERVICE] "+msg);
	}
}
