package ass06.seeds.controller;

import ass06.seeds.Service.MasterService;
import ass06.seeds.model.SeedsSet;
import ass06.seeds.view.MainView;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by Luca on 20/05/16.
 */
public class Controller implements IController{

    private MainView view;
    private SeedsSet seedsSet;
    private Flag flag;

    public Controller(MainView view,SeedsSet seedsSet){
        this.view = view;
        this.seedsSet = seedsSet;
    }

    @Override
    public void started(ArrayList<Point> points) {
        this.seedsSet.setPoints(points);
        this.flag = new Flag();
        //Avvia il master service che si occupa di creare il master ed aggiornare la gui
        new MasterService(this.view, this.seedsSet, this.flag);
    }

    @Override
    public void stopped() {
        this.flag.setValue(false);
    }
}
