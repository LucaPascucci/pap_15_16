package test.gof_ricci;

import java.util.concurrent.Callable;

/**
 * Compute the next state of subpart of the grid
 * @author aricci
 *
 */
public class ComputeStateTask implements Callable<Integer> {
	
	private int x0,y0,w,h;
	private CellGrid grid;
	
	public ComputeStateTask(CellGrid grid, int x0, int y0, int w, int h){
		this.x0 = x0;
		this.y0 = y0;
		this.w = w;
		this.h = h;
		this.grid = grid;
	}
	
	public Integer call(){
		int nAliveCells = 0;
		for (int i = 0; i < w; i++){
			for (int j = 0; j < h; j++){
				boolean alive = grid.computeNextCellState(x0+i,y0+j);
				if (alive){
					nAliveCells++;
				}
			}
		}
		return nAliveCells;
	}
}
