package ass08.trackBeatActors.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import java.awt.*;

/**
 * Created by Luca on 25/06/16.
 */
public class ControllerActor extends UntypedActor{

    private ActorRef view;
    private ActorRef model;

    private Dimension dim;
    private int heartbeat_TH;
    private int sec_th;

    public ControllerActor(Dimension dim, int heartbeat_th, int sec_th){
        this.dim = dim;
        this.heartbeat_TH = heartbeat_th;
        this.sec_th = sec_th;
    }

    /**
     * Crea Props per l'attore OracleActor.
     * @return Props per la crezione di questo attore
     */
    public static Props props(final Dimension dim, final int heartbeat_th, final int sec_th) {
        return Props.create(new Creator<ControllerActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public ControllerActor create() throws Exception {
                return new ControllerActor(dim, heartbeat_th, sec_th);
            }
        });
    }

    @Override
    public void preStart() throws Exception {
        this.view = getContext().actorOf(ViewActor.props(this.dim,this.heartbeat_TH,this.sec_th),"ViewActor");

    }

    @Override
    public void onReceive(Object message) throws Exception {

    }
}
