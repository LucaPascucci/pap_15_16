package ass06.gof.service;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Luca on 23/05/16.
 */
public class Master extends Thread {

    private static final int CORES = Runtime.getRuntime().availableProcessors() + 1;
    private static final int TASKS = 40;

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

    //suddivido il mondo per righe, e assegno il lavoro ad ogni task che ritornerà una lista parziale di punti
    public List<Point> computeEra() throws ExecutionException, InterruptedException {

        //Future = rappresenta il risultato di una computazione asincrona
        List<Future<List<Point>>> futureResults = new ArrayList<>();

        this.seeds.forEach(s -> this.worldSituation[(int)s.getY()][(int)s.getX()] = true); //converte il mondo da una lista di punti ad una matrice

        int start = 0;
        int step = this.worldSize.height / TASKS;

        //creo i task
        for (int i = 0; i < TASKS -1; i++){
            futureResults.add(this.executor.submit(new ComputeTask(this.worldSituation,start,start + step, this.worldSize,this.rules)));
            start += step;
        }
        futureResults.add(this.executor.submit(new ComputeTask(this.worldSituation,start,this.worldSize.height, this.worldSize,this.rules))); //task finale che prende le righe finali della matrice mondo

        ArrayList<Point> result = new ArrayList<>(); //lista di punti della nuova era
        for (Future<List<Point>> future : futureResults) {
            if (future.get() != null)
                result.addAll(future.get()); //prelevo il risultato della future (quando verrà completato) e faccio merge ad una lista di punti
        }

        this.executor.shutdown(); //blocca la possibilità di aggiungere nuovi task ed avvia la terminazione del ExecutorService
        this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); // aspetto che tutti i task siano stati completati
        return result;
    }
}
