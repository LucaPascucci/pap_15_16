package ass07.threads.controller;

import ass07.threads.monitor.GameMonitor;
import ass07.threads.service.PlayerWorker;
import ass07.threads.view.MainView;
import ass07.threads.model.Model;

/**
 * Created by Luca on 27/05/16.
 */
public class Controller implements IController{

    private MainView view;
    private Model model;
    private GameMonitor gameMonitor;

    public Controller(MainView view, Model model){
        this.view = view;
        this.model = model;
        this.gameMonitor = new GameMonitor(this.model.getPlayersNumber());
    }

    @Override
    public void start() {
        this.gameMonitor.setActiveGame(true);
        this.view.setMagicNumber(this.model.getMagicNumber());
        this.model.getPlayerDataMap().forEach((k,v) -> new PlayerWorker(this.gameMonitor,v,this.model,this.view).start());
    }

    @Override
    public void stop() {
        this.gameMonitor.setActiveGame(false);
    }

    @Override
    public void reset() {
        this.model.prepareGame();
        this.gameMonitor = new GameMonitor(this.model.getPlayersNumber());
    }
}
