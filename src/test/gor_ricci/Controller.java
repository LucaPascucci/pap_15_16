package test.gor_ricci;

public class Controller implements InputListener {

	private CellGrid grid;
	private Flag 	stopFlag;
	private View view;
	private int frameRate;
	private int nTasks;
	
	public Controller(CellGrid grid, View view, int frameRate, int nTasks){
		this.grid = grid;
		this.view = view;
		this.frameRate = frameRate;
		this.nTasks = nTasks;
	}
	
	public void started(){
		stopFlag = new Flag();
		new Master(grid,view,stopFlag,frameRate,nTasks).start();
	}

	public void stopped() {
		stopFlag.set();
	}

}
