package lab06.executors.quad01;

public class Main {

	public static void main(String args[]) throws Exception {	

		double a = 0;
		double b = 3;		
		int nTasks = 10;
		int poolSize = Runtime.getRuntime().availableProcessors() + 1; //per saturare tutte le cpu disponibili
		
		QuadratureService service = new QuadratureService(nTasks, poolSize);
		double result = service.compute((double x) -> Math.sin(x), a, b);
		System.out.println("Result: " + result);
		
		System.exit(0);
	}
	
}
