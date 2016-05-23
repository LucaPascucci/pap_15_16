package test.gof_ricci;

/**
 * Assignment 06 - Game of Life with Executors
 * 
 * Concurrent version. 
 * 
 * @author aricci
 *
 */
public class GameOfLife {
	public static void main(String[] args) {
		
		int w = 1000;
		int h = 1000;
		int nTasks = 50;
		int frameRate = 100;
		
		CellGrid grid = new CellGrid(w,h);
		grid.initRandom(250000);
		
		/*
		grid.drawGlider(10, 10);
		grid.drawGlider(100, 50);
		grid.drawBlock(30, 70);
		*/
		
		View view = new View(800, 600, grid);
		Controller controller = new Controller(grid, view, frameRate, nTasks);
		view.addListener(controller);
		view.setVisible(true);
	}

}
