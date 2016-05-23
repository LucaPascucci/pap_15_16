package test.gof2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Master extends Thread{

	private View view;
	private Model model;
	private int speed,nTasks=50,from=0,step=0;
	private ExecutorService executor;
	private Barrier barrier=new Barrier(nTasks);
	private Flag flag;
	
	public Master(View view,Model model,int speed,Flag flag){
		this.view=view;
		this.model=model;
		this.speed=speed;
		this.flag=flag;
		executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
		step=model.getRow()/nTasks;
	}
	
	public void run(){
		
		model.setCells(view.getCellsLive());
		
		while(!flag.getState()){
			
			from=0;
			
			for(int i=0;i<nTasks-1;i++){
				executor.submit(new WorkerCalculate(from,from+step,model,barrier));
				from+=step;
			}
			
			executor.submit(new WorkerCalculate(from,model.getRow(),model,barrier));
			
			barrier.go();
			
			from=0;
			
			for(int i=0;i<nTasks-1;i++){
				executor.submit(new WorkerRefresh(from,from+step,model,barrier));
				from+=step;
			}
			
			executor.submit(new WorkerRefresh(from,model.getRow(),model,barrier));
			
			barrier.go();
			view.setCellsLive(model.getTable());
			
			if(!flag.getState()){
				try {
					sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
