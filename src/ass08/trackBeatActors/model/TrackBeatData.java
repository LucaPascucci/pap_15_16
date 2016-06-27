package ass08.trackBeatActors.model;

import pap.ass08.pos.P2d;

/**
 * Created by Luca on 25/06/16.
 */
public class TrackBeatData {

    private int heartbeat;
    private P2d pos;

    public TrackBeatData(){
        this.heartbeat = 0;
        this.pos = new P2d(0.0,0.0);
    }

    public TrackBeatData(int heartbeat, P2d pos){
        this.heartbeat = heartbeat;
        this.pos = pos;
    }

    public int getHeartbeat() {
        return this.heartbeat;
    }

    public P2d getPos(){
        return this.pos;
    }

    public void sum(TrackBeatData data){
        this.heartbeat += data.heartbeat;
        this.pos = new P2d(this.pos.getX() + data.pos.getX(), this.pos.getY() + data.pos.getY());
    }

    @Override
    public String toString() {
        return "HB: " + this.heartbeat + " Pos: " + this.pos.getX() + " : " + this.pos.getY();
    }
}
