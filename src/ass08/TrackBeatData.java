package ass08;

import pap.ass08.pos.P2d;

/**
 * Rappresenta i dati congiunti del sensore di battito cardiaco e della posizione
 *
 * Created by Luca on 25/06/16.
 */
public class TrackBeatData {

    private int heartbeat;
    private P2d pos;

    public TrackBeatData(int heartbeat, P2d pos){
        this.heartbeat = heartbeat;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "HB: " + this.heartbeat + " Pos: " + this.pos.getX() + " : " + this.pos.getY();
    }
}
