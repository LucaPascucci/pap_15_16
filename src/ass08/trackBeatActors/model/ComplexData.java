package ass08.trackBeatActors.model;

/**
 * Classe che contiene tutte le informazioni utili per aggiornare la View
 *
 * Created by Luca on 25/06/16.
 */
public class ComplexData {

    private TrackBeatData currData;
    private TrackBeatData maxData;
    private double speed;
    private double HB_AVG;
    private boolean activeAlarm;

    public ComplexData (TrackBeatData currData, TrackBeatData maxData, double speed, double hb_AVG, boolean activeAlarm) {
        this.currData = currData;
        this.maxData = maxData;
        this.speed = speed;
        this.HB_AVG = hb_AVG;
        this.activeAlarm = activeAlarm;
    }

    public TrackBeatData getCurrData() {
        return this.currData;
    }

    public TrackBeatData getMaxData() {
        return this.maxData;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getHB_AVG() {
        return this.HB_AVG;
    }

    public boolean isActiveAlarm() {
        return this.activeAlarm;
    }
}
