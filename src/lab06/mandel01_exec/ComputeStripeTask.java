package lab06.mandel01_exec;

public class ComputeStripeTask implements Runnable {

	private MandelbrotSet result;
	private int x0;
	private int x1;
	private Complex c0;
	private double diam;
	private Flag stopFlag;

	public ComputeStripeTask(int x0, int x1, Complex c0, double diam, MandelbrotSet result, Flag stopFlag) {
		this.result = result;
		this.x0 = x0;
		this.x1 = x1;
		this.diam = diam;
		this.c0 = c0;
		this.stopFlag = stopFlag;
	}

	public void run() {
		try {
			log("start working ");
			int nSteps = 4;
			int dx = (this.x1 - this.x0) / nSteps;
			//boolean stopped = false;
			for (int i = 0; i < nSteps; i++){
				if (this.stopFlag.isSet()){
					break;
				}
				this.result.computeSlice(x0, x0 + dx, c0, diam);
				this.x0 += dx;
			}
			if (this.stopFlag.isSet()){
				log("task completed");
			} else {
				log("task interrupted");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void log(String msg){
		synchronized(System.out){
			System.out.println(msg);
		}
	}

}
