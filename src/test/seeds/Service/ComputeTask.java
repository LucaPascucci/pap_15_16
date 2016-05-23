package test.seeds.Service;


import test.seeds.model.SeedsSet;

import java.awt.Point;
import java.util.concurrent.Callable;

/**
 * Created by Luca on 21/05/16.
 */
public class ComputeTask implements Callable<Point> {

    private SeedsSet seedsSet;
    private boolean[][] neighbours;
    private int x,y;

    public ComputeTask(SeedsSet set, boolean[][] neighbours, int x, int y){
        this.seedsSet = set;
        this.neighbours = neighbours;
        this.x = x;
        this.y = y;
    }


    @Override
    public Point call() throws Exception {
        return seedsSet.computePoint(neighbours,x,y);
    }
}
