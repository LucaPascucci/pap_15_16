package lab06.executors.quad03;

import java.util.concurrent.Callable;


public class ComputeAreaTask implements Callable<Double>  {

	private IFunction mf;
	private double a, b;
	private int id;

	public ComputeAreaTask(int id, double a, double b, IFunction mf) {
		this.id = id;
		this.mf = mf;
		this.a = a;
		this.b = b;
	}

	public Double call() {
		log("Executing from " + a + " to" + b);
		double sum = 0;
		double step = (b - a) / 1000;
		double x = a;
		for (int i = 0; i < 1000; i++) {
			sum += step * mf.eval(x);
			x += step;
		}
		log("Computed result: " + sum);
		return sum;
	}

	private void log(String msg) {
		System.out.println("[TASK-"+ this.id + "] " + msg);
	}
}
