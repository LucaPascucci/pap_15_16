package ass06.gof.service;

import ass06.gof.controller.Flag;
import ass06.gof.model.SeedsSet;
import ass06.gof.view.MainView;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Luca on 23/05/16.
 */
public class QuadratureService extends Thread{

    private static final long SLEEP = 100;

    private MainView view;
    private SeedsSet seeds;
    private Flag flag;

    public QuadratureService(MainView view, SeedsSet seeds, Flag flag){
        this.view = view;
        this.seeds = seeds;
        this.flag = flag;
    }

    //avvia il Master che si occupa a sua volta di creare i task
    @Override
    public void run() {
        super.run();

        try {
            //controllo che il mondo non sia vuoto
            if (this.seeds.getSeeds().isEmpty()){
                this.flag.setValue(false);
                this.view.emptyWorldMessage();
            }

            while (this.flag.getValue()){
                this.seeds.incEra();

                long startTime = System.nanoTime();
                Master master = new Master(this.seeds.getSeeds(), this.seeds.getWorldSize(),this.seeds.getRules());
                List<Point> result = master.computeEra();
                //aggiorno il modello e la view
                this.seeds.setNewSeeds(result);
                this.view.updateAliveSeeds(result);
                this.view.updateInfo(this.seeds.getEra(),this.seeds.getSeeds().size(),(System.nanoTime() - startTime)/1000);

                Thread.sleep(SLEEP);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
