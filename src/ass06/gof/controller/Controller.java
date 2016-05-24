package ass06.gof.controller;


import ass06.gof.model.SeedsSet;
import ass06.gof.service.QuadratureService;
import ass06.gof.view.MainView;

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
    public void started() {
        this.flag.setValue(true);
        new QuadratureService(this.view, this.seedsSet, this.flag).start();
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
        this.view.setAliveSeeds(this.seedsSet.getSeeds().size());

    }

    public void changeRules(){
        this.seedsSet.changeRules();
    }
}
