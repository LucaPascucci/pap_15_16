package test.gof2;

public class WorkerRefresh extends Worker implements Runnable {

	public WorkerRefresh(int start,int end,Model model,Barrier barrier){
		super(start,end,model,barrier);
	}
	
	@Override
	public void run() {
		model.refresh(start, end);
		barrier.dec();
	}
}
