package lab06.executors.quad01;

public class QuadratureResult {  //Monitor
	
	private double sum; 
	private int nTotalResultsToWait;
	private int nResultsArrived;
	
	public QuadratureResult(int nResults){
		nTotalResultsToWait = nResults;
		nResultsArrived = 0;
	}
	
	public synchronized void add(double value){
		sum += value;
		nResultsArrived++;
		if (nResultsArrived >= nTotalResultsToWait){
			notifyAll();
		}
	}

	public synchronized double getResult() throws InterruptedException {
		while (nResultsArrived < nTotalResultsToWait){
			wait();
		}
		return sum;
	}
}
