package lab06.executors.quad01;


//ogni task non deve essere collegato ad un'altro
public class ComputeAreaTask implements Runnable {

	private QuadratureResult result;
	private IFunction mf;
	private double a, b;
	private int id;
	
	public ComputeAreaTask(int id,double a, double b, IFunction mf, QuadratureResult result) {
		this.id = id;
		this.mf = mf;
		this.a = a;
		this.b = b;
		this.result = result;
	}

	public void run() {
				log("Executing from " + a + " to " + b);
				double sum = 0;
				double step = (b - a) / 1000;
				double x = a;
				for (int i = 0; i < 1000; i++) {
					sum += step * mf.eval(x);
					x += step;
				}
				result.add(sum); //ogni task l'add sul monitor
				log("Added result: " + sum);
	}

	private void log(String msg) {
		System.out.println("[TASK-"+ this.id + "] " + msg);
	}
}
