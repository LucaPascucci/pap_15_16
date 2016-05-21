package ass06.seeds2.controller;


import ass06.seeds2.model.SeedsSet;
import ass06.seeds2.view.MainView;

/**
 * Created by Luca on 20/05/16.
 */
public class Controller implements IController {

    private MainView view;
    private SeedsSet seedsSet;
    private Flag flag;

    public Controller(MainView view, SeedsSet seedsSet){
        this.view = view;
        this.seedsSet = seedsSet;
        this.flag = new Flag();
    }

    @Override
    public void started() {this.flag.setValue(true);
    }

    @Override
    public void stopped() {
        this.flag.setValue(false);

    }

    @Override
    public void reset() {
        this.seedsSet.emptyWorld();
    }

    @Override
    public void manageSeed(int r, int c, boolean value){

        if (value){
            this.seedsSet.addSeed(r,c);
        } else {
            this.seedsSet.removeSeed(r,c);
        }

    }
}
