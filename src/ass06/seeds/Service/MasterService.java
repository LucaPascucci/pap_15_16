package ass06.seeds.Service;

import ass06.seeds.controller.Flag;
import ass06.seeds.model.SeedsSet;
import ass06.seeds.view.MainView;

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
            //TODO informare che non ci sono punti vivi nel mondo
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
                //TODO cambiare testo della view con il numero di era

                //TODO aggiornare il numero di celle vive

                if (!this.flag.getValue()){
                    //TODO informare che si è fermata l'esecuzione
                }
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
