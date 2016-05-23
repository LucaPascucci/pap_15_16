package ass06.seeds2.service;

import java.awt.*;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Luca on 23/05/16.
 */
public class Master extends Thread {

    private static final int CORES = Runtime.getRuntime().availableProcessors() + 1;

    private List<Point> seeds;
    private Executor executor = Executors.newFixedThreadPool(CORES);
    private boolean[][] worldSituation;
    private Dimension worldSize;

    public Master(List<Point> seeds, Dimension worldSize){
        this.seeds = seeds;
        this.worldSize = worldSize;
        this.worldSituation = new boolean[this.worldSize.height][this.worldSize.width];;
    }

    public List<Point> computeEra(){

        this.seeds.stream().forEach(s -> this.worldSituation[(int)s.getY()][(int)s.getX()] = true);

        return this.seeds;
    }
}
