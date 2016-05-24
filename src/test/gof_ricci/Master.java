package test.gof_ricci;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * This master uses an Executor to parallelize the job.
 * 
 * @author aricci
 *
 */
public class Master extends Thread {

	private View view;
	private CellGrid grid;
	private Flag stopFlag;
	private long period;
	private int nAliveCells;
	private int nTasks;
	private List<Future<Integer>> resList;
	
	public Master(CellGrid grid, View view, Flag stopFlag, int frameRate, int nTasks){
		this.grid = grid;
		this.view = view;
		this.stopFlag = stopFlag;
		this.nTasks = nTasks;
		period = 1000/frameRate;
		resList = new ArrayList<>();
	}
	
	public void run(){
		try {

			/* set up the task list */
			List<ComputeStateTask> tasks = new ArrayList<ComputeStateTask>();
			int x0 = 0;
			int dx = grid.getWidth() / nTasks;
			for (int i = 0; i < nTasks - 1; i++){
				tasks.add(new ComputeStateTask(grid,x0,0,dx,grid.getHeight()));
				x0 += dx;
			}
			tasks.add(new ComputeStateTask(grid,x0,0,grid.getWidth()-x0,grid.getHeight()));

			/* set up the executor */
			ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
			
			while (!stopFlag.isSet()){
				long current = System.currentTimeMillis();				
				long currentNano = System.nanoTime();				

				/* assign tasks */
				nAliveCells = 0;
				tasks.stream().forEach(t -> {
					resList.add(exec.submit(t));
				});				
				
				/* collect results */
				for (Future<Integer> f: resList){
					try {
						nAliveCells += f.get();
					} catch (Exception ex){}
				};
				resList.clear();	
				
				/* swap the new state */
				grid.swap();
				long dt = (System.nanoTime() - currentNano)/1000;
				
				/* update the view */
				view.update(nAliveCells, dt);
				
				/* synch with the frame rate */
				waitForNextFrame(current);
			}			
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	protected void waitForNextFrame(long current){
		long dt = System.currentTimeMillis() - current;
		if (dt < period){
			try {
				Thread.sleep(period-dt);
			} catch (Exception ex){}
		}
	}
	
}
