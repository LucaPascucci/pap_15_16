package ass06.gof.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Luca on 23/05/16.
 */
public class Master extends Thread {

    private static final int CORES = Runtime.getRuntime().availableProcessors() + 1;
    private static final int TASKS = 30;

    private List<Point> seeds;
    private ExecutorService executor = Executors.newFixedThreadPool(CORES);
    private boolean[][] worldSituation;
    private Dimension worldSize;
    private boolean rules;

    public Master(List<Point> seeds, Dimension worldSize, boolean rules){
        this.seeds = seeds;
        this.worldSize = worldSize;
        this.worldSituation = new boolean[this.worldSize.height][this.worldSize.width];
        this.rules = rules;
    }

    public List<Point> computeEra(){

        List<Future<List<Point>>> futureResults = new ArrayList<>();

        this.seeds.stream().forEach(s -> this.worldSituation[(int)s.getY()][(int)s.getX()] = true);

        int start = 0;
        int step = this.worldSize.height / TASKS;

        for (int i = 0; i < TASKS -1; i++){
            futureResults.add(this.executor.submit(new ComputeTask(this.worldSituation,start,start + step, this.worldSize,this.rules)));
            start += step;
        }
        futureResults.add(this.executor.submit(new ComputeTask(this.worldSituation,start,this.worldSize.height, this.worldSize,this.rules)));


        ArrayList<Point> result = new ArrayList<>();
        for (Future<List<Point>> future : futureResults) {
            try {
                if (future.get() != null)
                    result.addAll(future.get());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        this.executor.shutdown();
        return result;
    }
}
