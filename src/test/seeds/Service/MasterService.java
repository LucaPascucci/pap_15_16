package test.seeds.Service;

import test.seeds.controller.Flag;
import test.seeds.model.SeedsSet;
import test.seeds.view.MainView;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Luca on 21/05/16.
 */
public class MasterService extends Thread{

    private MainView view;
    private SeedsSet seedsSet;
    private Flag flag;

    public MasterService(MainView view, SeedsSet set, Flag flag){
        this.view = view;
        this.seedsSet = set;
        this.flag = flag;
    }

    @Override
    public void run() {
        super.run();

        if (this.seedsSet.getPoints().isEmpty()){
            this.flag.setValue(false);
        }

        int era = 0;
        try {
            while (this.flag.getValue()) {
                Thread.sleep(50);

                Master master = new Master(this.seedsSet);
                ArrayList<Point> result = master.compute();
                this.seedsSet.setPoints(result);
                //this.view.setUpdated(result);

                era++;

                if (!this.flag.getValue()){
                }
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
