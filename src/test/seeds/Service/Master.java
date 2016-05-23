package test.seeds.Service;

import test.seeds.model.SeedsSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Luca on 21/05/16.
 */
public class Master extends Thread {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    private SeedsSet seedsSet;
    private Executor executor = Executors.newFixedThreadPool(POOL_SIZE);
    private boolean[][] worldSituation;

    public Master(SeedsSet set){
        this.seedsSet = set;
        this.worldSituation = new boolean[this.seedsSet.getSizeX()][this.seedsSet.getSizeY()];
    }

    public ArrayList<Point> compute(){




        return null;
    }
}
