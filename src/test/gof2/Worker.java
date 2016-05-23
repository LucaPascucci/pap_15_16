package test.gof2;

public class Worker {
	protected int start,end;
	protected Model model;
	protected Barrier barrier;
	
	public Worker(int start,int end,Model model,Barrier barrier){
		this.start=start;
		this.end=end;
		this.model=model;
		this.barrier=barrier;
	}
}
