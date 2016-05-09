package ass05.MinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Luca on 06/05/16.
 */
public class MultiThread {

    private static final int N_CORES = Runtime.getRuntime().availableProcessors();
    private static double MIN_DISTANCE = Double.MAX_VALUE;
    private static P2d CLOSER_POINT = new P2d(0.0,0.0);

    public static void main(String args[]){

        long startTotalTime = System.currentTimeMillis();

        int n_points;
        if (args.length == 1) {
            n_points = Integer.parseInt(args[0]);
        }else {
            n_points = 10000000;
        }

        //TODO ripartire indici in maniera equa (senza che l'ultimo thread prenda più)
        int[] steps = new int[N_CORES +1];
        int mod = n_points % N_CORES;
        steps[0] = 0;
        for(int i = 1;i < steps.length ;i++){
            steps[i] = mod-- > 0 ? 1 + steps[i-1]:steps[i-1];
            //System.out.println(steps[i]);
        }

        int step = n_points / N_CORES;
        P2d centroid = new P2d(0.0,0.0);

        List<P2d> points = new ArrayList<>();
        List<Thread> creators = new ArrayList<>();

        //creazione dei punti attraverso multi-thread
        long startTime = System.currentTimeMillis();
        IntStream.range(0,N_CORES).forEach(i -> {
            Thread creator = new Thread(() -> {
                int start = step * i;
                int stop = start + step;
                if (i == N_CORES - 1){
                    stop = n_points;
                }
                System.out.println("Creator " + (i + 1) + ": start: " + start + " - stop: " + stop);
                IntStream.range(start, stop).forEach(j -> {
                    P2d curr = new P2d();
                    synchronized (points){
                        centroid.sum(curr);
                        points.add(curr);
                    }
                });

            });
            creators.add(creator);
            creator.start();
        });

        //Join sui creatori dei punti
        creators.stream().forEach(w -> {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("\nCreated " + points.size() + " points in millis: " + (System.currentTimeMillis() - startTime) + '\n');

        //Definizione del baricentro
        centroid.setX(centroid.getX() / (double) n_points);
        centroid.setY(centroid.getY() / (double) n_points);
        System.out.println("Centroid Pos: " + centroid.toString() + '\n');

        //ricerca del punto più vicino al baricentro multi-thread
        List<Researcher> researchers = new ArrayList<>();

        startTime = System.currentTimeMillis();
        IntStream.range(0, N_CORES).forEach(i -> {
            int start = step * i;
            int stop = start + step;
            if (i == N_CORES - 1){
                stop = n_points;
            }
            Researcher researcher = new Researcher((i + 1), start, stop, points, centroid);
            researchers.add(researcher);
            researcher.start();

        });

        //Join sulla ricerca multi-thread
        researchers.stream().forEach(r -> {
            try {
                r.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("\nFound closer Point: " + CLOSER_POINT.toString() + " in " + (System.currentTimeMillis() - startTime) + " Millis");
        System.out.println("Distance from centroid = " + MIN_DISTANCE);

        System.out.println("\nExecution time: " + (System.currentTimeMillis() - startTotalTime) + " millis");
    }

    private static class Researcher extends Thread {

        private int id;
        private int start;
        private int stop;
        private List<P2d> points;
        private P2d centroid;

        public Researcher (int id, int start, int stop, List<P2d> points, P2d centroid) {
            this.id = id;
            this.start = start;
            this.stop = stop;
            this.points = points;
            this.centroid = centroid;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("Researcher " + this.id + ": start: " + this.start + " - stop: " + this.stop);
            double local_min_distance = Double.MAX_VALUE;
            P2d local_closer_point = new P2d(0.0,0.0);
            for (; this.start < this.stop ; start++){
                P2d curr_point = this.points.get(this.start);
                double curr_distance = P2d.distance(this.centroid,curr_point);
                if (local_min_distance > curr_distance){
                    local_min_distance = curr_distance;
                    local_closer_point = curr_point;
                }
            }
            this.syncMin(local_min_distance,local_closer_point);
        }

        private synchronized void syncMin(double curr_distance, P2d curr_point){
            if (MIN_DISTANCE > curr_distance){
                CLOSER_POINT = curr_point;
                MIN_DISTANCE = curr_distance;
            }
        }
    }
}
