package ass08.trackBeatRX.model;

import ass08.HeartbeatStream;
import pap.ass08.pos.Flag;
import pap.ass08.pos.P2d;
import pap.ass08.pos.PosSensor;
import rx.Observable;

/**
 * Created by Luca on 10/06/16.
 */
public class Model {

    private int heartBeatTH;
    private int secondsTH;
    private TrackBeatData maxHeartBeatData;
    private TrackBeatData previousData;
    private double AVG_HB;
    private double speed;
    private double countData;
    private boolean activeAlarm;
    private long lastUpdate;

    public Model (int heartBeatTH, int secondsTH){
        this.heartBeatTH = heartBeatTH;
        this.secondsTH = secondsTH;

        this.lastUpdate = 0;
        this.countData = 0;
        this.maxHeartBeatData = new TrackBeatData();
        this.AVG_HB = 0;
        this.speed = 0.0;
        this.activeAlarm = false;
    }

    public double getSpeed(){
        return this.speed;
    }

    public double getAVG_HB(){
        return (this.AVG_HB / this.countData);
    }

    public TrackBeatData getMaxHeartBeatData(){
        return this.maxHeartBeatData;
    }

    public boolean getActiveAlarm(){
        return this.activeAlarm;
    }

    public int getHeartBeatTH() {
        return this.heartBeatTH;
    }

    public int getSecondsTH() {
        return this.secondsTH;
    }

    public Observable<TrackBeatData> makeObservable(int period, Flag flag){

        PosSensor posSensor = new PosSensor();
        HeartbeatStream heartbeatStream = new HeartbeatStream(period,flag);

        Observable<P2d> posObs = posSensor.makeObservable(period,flag);
        Observable<Integer> heartbeatObs = heartbeatStream.makeObservable();

        return Observable.zip(heartbeatObs,posObs,(HBData,PosData) -> {
            TrackBeatData data = new TrackBeatData(HBData,PosData);
            this.addTrackBeatData(data);
            return data;
        });
    }

    private void addTrackBeatData(TrackBeatData data){
        long currTime = System.currentTimeMillis();
        this.countData++;

        this.AVG_HB += data.getHeartbeat();

        if (this.maxHeartBeatData.getHeartbeat() <= data.getHeartbeat()){
            this.maxHeartBeatData = data;
        }

        if (this.countData > 1){
            long time = System.currentTimeMillis() - this.lastUpdate;
            double distance = P2d.distance(this.previousData.getPos(),data.getPos());
            this.speed = distance / time;
        }
        this.previousData = data;
        this.lastUpdate = currTime;

        //TODO controllo per allarme

    }
}
