package ass08.trackBeatActors.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import ass08.HeartbeatSensor;
import ass08.trackBeatActors.model.ComplexData;
import ass08.trackBeatActors.model.EAction;
import ass08.trackBeatActors.model.TrackBeatData;
import ass08.trackBeatActors.msgs.ActionMsg;
import ass08.trackBeatActors.msgs.AttachMsg;
import ass08.trackBeatActors.msgs.NewDataMsg;
import pap.ass08.pos.P2d;
import pap.ass08.pos.PosSensor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by Luca on 25/06/16.
 */
public class ModelActor extends UntypedActor {

    private ActorRef controller;

    private int hb_TH;
    private int sec_TH;
    private HeartbeatSensor heartbeatSensor;
    private PosSensor posSensor;
    private TrackBeatData maxHeartBeatData;
    private TrackBeatData previousData;
    private double AVG_HB;
    private double speed;
    private double countData;
    private boolean activeAlarm;
    private long lastUpdate;
    private long startAlartTime = 0;
    private boolean startFlag;

    private ModelActor (int hb_th, int sec_th){
        this.hb_TH = hb_th;
        this.sec_TH = sec_th;

        this.setup();
    }

    /**
     * Crea Props per l'attore ModelActor.
     * @return Props per la creazione di questo attore
     */
    public static Props props(final int heartbeat_th, final int sec_th) {
        return Props.create(new Creator<ModelActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public ModelActor create() throws Exception {
                return new ModelActor(heartbeat_th, sec_th);
            }
        });
    }

    @Override
    public void onReceive(Object message) throws Exception {

        //Collegamento tra model e controller
        if (message instanceof AttachMsg){
            this.controller = getSender();
        }

        //Messaggi contenenti azioni
        if (message instanceof ActionMsg){
            switch (((ActionMsg) message).getAction()){
                //generazione/acquisizione di nuovi dati
                case NEW_DATA:
                    this.getNextData();
                    break;
                //avvio dei sensori e delle generazione/acquisizione di nuovi dati
                case START:
                    this.heartbeatSensor = new HeartbeatSensor();
                    this.posSensor = new PosSensor();
                    this.getNextData();
                    break;
                //Ferma il ciclo di generazione/acquisizione di dati
                case STOP:
                    this.startFlag = false;
                    break;
                //Ripristina il contenuto del Model
                case RESET:
                    this.setup();
                    break;
                //Aggiornamento dei TH
                case UPDATE_BHTH:
                    this.updateHB_HT(((ActionMsg) message).getValue());
                    break;
                case UPDATE_SECTH:
                    this.updateSEC_HT(((ActionMsg) message).getValue());
                    break;
            }
        }
    }

    private void updateHB_HT(int value){
        this.hb_TH = value;
        this.activeAlarm = false;
        this.startAlartTime = 0;
    }

    private void updateSEC_HT(int value){
        this.sec_TH = value;
        this.activeAlarm = false;
        this.startAlartTime = 0;
    }

    private void setup(){
        this.heartbeatSensor = null;
        this.posSensor = null;
        this.lastUpdate = 0;
        this.countData = 0;
        this.maxHeartBeatData = new TrackBeatData();
        this.AVG_HB = 0;
        this.speed = 0.0;
        this.activeAlarm = false;
        this.startAlartTime = 0;
        this.startFlag = true;
    }

    //Aquisisce nuovi dati dai sensori
    private void getNextData(){
        TrackBeatData currData = new TrackBeatData(this.heartbeatSensor.getCurrentValue(),this.posSensor.getCurrentValue());
        long currTime = System.currentTimeMillis();
        this.countData++;

        this.AVG_HB += currData.getHeartbeat();

        //aggiorna il valore massimo
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

        //allarme battito cardiaco elevato prolungato
        if (this.hb_TH <= currData.getHeartbeat()){
            if (this.startAlartTime == 0){
                //memorizzo il tempo del primo battito cardiaco elevato
                this.startAlartTime = currTime;
            }
            //Controllo temporale
            if (System.currentTimeMillis() - this.startAlartTime >= this.sec_TH * 1000){
                this.activeAlarm = true;
            }
        } else {
            //Rimuove allarme ed azzera il tempo
            this.startAlartTime = 0;
            this.activeAlarm = false;
        }

        //quando verrà premuto il bottone STOP allora il ciclo di generazione di dati terminerà e non verranno inviati altri dati al controller
        if (this.startFlag){
            //Invia i dati da visualizzare nella view al controller
            this.controller.tell(new NewDataMsg(new ComplexData(currData,this.maxHeartBeatData,this.speed,this.AVG_HB/this.countData,this.activeAlarm)),getSelf());

            //Inserisce un messaggio nello scheduler che verrà inviato con 500 mills di ritardo, in pratica crea un ciclo di generazione dei dati
            this.getContext().system().scheduler().scheduleOnce(Duration.create(500, TimeUnit.MILLISECONDS), getSelf(), new ActionMsg(EAction.NEW_DATA),this.getContext().system().dispatcher(), this.getSelf());
        }
    }
}
