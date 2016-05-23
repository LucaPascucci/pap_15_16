package test.gof2;

public class WorkerCalculate extends Worker implements Runnable{

	public WorkerCalculate(int start,int end,Model model,Barrier barrier){
		super(start,end,model,barrier);
	}
	
	@Override
	public void run() {
		model.calculate(start, end);
		barrier.dec();
	}

}
