package ass08.trackBeatRX.controller;

import ass08.trackBeatRX.model.Model;
import ass08.trackBeatRX.model.TrackBeatData;
import ass08.trackBeatRX.view.MainView;
import pap.ass08.pos.Flag;
import rx.Observable;

/**
 * Created by Luca on 09/06/16.
 */
public class Controller implements IController{

    private final static int WAIT_TIME = 100;

    private MainView view;
    private Model model;
    private Flag flag;

    public Controller (MainView view, Model model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void start() {
        this.flag = new Flag();
        Observable<TrackBeatData> stream = this.model.makeObservable(WAIT_TIME,this.flag);
        stream.subscribe((trackBeatData) ->
                this.view.updateView(trackBeatData,this.model.getAVG_HB(),this.model.getMaxHeartBeatData(),this.model.getSpeed(),this.model.getActiveAlarm()));
    }

    @Override
    public void stop() {
        this.flag.set();
    }

    @Override
    public void reset() {
        this.model.setup();
    }

    @Override
    public void modifyHBTH(boolean action, int value) {
        if (action){
            this.model.setHeartBeatTH(value);
        } else {
            this.view.restoreHBTH(this.model.getHeartBeatTH());
        }
    }

    @Override
    public void modifySecTH(boolean action, int value) {
        if (action){
            this.model.setSecondsTH(value);
        } else {
            this.view.restoreSecTH(this.model.getSecondsTH());
        }
    }
}
