package lab06.executors.quad02;

public class QuadratureResult {
	
	private double sum; 
	
	public QuadratureResult(){
		sum = 0;
	}
	
	public synchronized void add(double value){
		sum += value;
	}

	public synchronized double getResult(){
		return sum;
	}
}
