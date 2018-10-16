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
    private long startAlartTime = 0;

    public Model (int heartBeatTH, int secondsTH){
        this.heartBeatTH = heartBeatTH;
        this.secondsTH = secondsTH;

        this.setup();
    }

    public void setup(){
        this.lastUpdate = 0;
        this.countData = 0;
        this.maxHeartBeatData = new TrackBeatData();
        this.AVG_HB = 0;
        this.speed = 0.0;
        this.activeAlarm = false;
        this.startAlartTime = 0;
    }

    public void setHeartBeatTH(int value){
        this.heartBeatTH = value;
        this.activeAlarm = false;
        this.startAlartTime = 0;
    }

    public void setSecondsTH(int value){
        this.secondsTH = value;
        this.activeAlarm = false;
        this.startAlartTime = 0;
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

    //generazione di un unico oggetto Observable che unisce i valori di posizione e battito cardiaco
    public Observable<TrackBeatData> makeObservable(int period, Flag flag){

        //Creo i sensori
        PosSensor posSensor = new PosSensor();
        HeartbeatStream heartbeatStream = new HeartbeatStream(period,flag);

        //Creo gli observable dei due sensori
        Observable<P2d> posObs = posSensor.makeObservable(period,flag);
        Observable<Integer> heartbeatObs = heartbeatStream.makeObservable();

        /*  Returns an Observable that emits the results of a specified
            combiner function applied to combinations of two items emitted,
            in sequence, by two other Observables.
        */
        return Observable.zip(heartbeatObs,posObs,(HBData,PosData) -> {
            TrackBeatData data = new TrackBeatData(HBData,PosData);
            this.addTrackBeatData(data); //ad ogni TrackBeatData generato esegue l'aggiornamento del modello
            return data;
        });
    }

    //Riceve il dato generato durante il subscribe al Observable e aggiorna il model
    private void addTrackBeatData(TrackBeatData currData){
        long currTime = System.currentTimeMillis();
        this.countData++;

        this.AVG_HB += currData.getHeartbeat();

        //aggiorna il valore massimo memorizzato
        if (this.maxHeartBeatData.getHeartbeat() <= currData.getHeartbeat()){
            this.maxHeartBeatData = currData;
        }

        //calcola la velocità
        if (this.countData > 1){
            long time = System.currentTimeMillis() - this.lastUpdate;
            double distance = P2d.distance(this.previousData.getPos(),currData.getPos());
            this.speed = distance / time;
        }
        this.previousData = currData;
        this.lastUpdate = currTime;

        //controllo per allarme battito cardiaco elevato prolungato
        if (this.heartBeatTH <= currData.getHeartbeat()){
            if (this.startAlartTime == 0){
                //memorizzo il tempo del primo battito cardiaco elevato rilevato
                this.startAlartTime = currTime;
            }
            //Controllo temporale del battito cardiaco elevato
            if (System.currentTimeMillis() - this.startAlartTime >= this.secondsTH * 1000){
                this.activeAlarm = true;
            }
        } else {
            //Rimuove allarme ed azzera il tempo
            this.startAlartTime = 0;
            this.activeAlarm = false;
        }

    }
}
