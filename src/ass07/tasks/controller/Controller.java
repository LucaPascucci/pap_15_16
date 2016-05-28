package ass07.tasks.controller;

import ass07.tasks.view.MainView;
import ass07.tasks.model.Model;
import ass07.tasks.service.Master;

/**
 * Created by Luca on 27/05/16.
 */
public class Controller implements IController{

    private MainView view;
    private Model model;
    private TurnFlag turnFlag;
    private WinnerFlag winnerFlag;

    public Controller(MainView view, Model model){
        this.view = view;
        this.model = model;
        this.turnFlag = new TurnFlag();
        this.winnerFlag = new WinnerFlag();
    }

    @Override
    public void started() {
        this.turnFlag.setValue(true);
        this.view.setMagicNumber(this.model.getMagicNumber());
        new Master(this.model,this.view,this.turnFlag,this.winnerFlag).start();
    }

    @Override
    public void stopped() {
        this.turnFlag.setValue(false);
    }

    @Override
    public void reset() {
        this.model.prepareGame();
        this.winnerFlag = new WinnerFlag();
    }
}
