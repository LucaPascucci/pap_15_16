package ass08.trackBeatActors.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import ass08.trackBeatActors.model.EnumAction;
import ass08.trackBeatActors.msgs.ActionMsg;
import ass08.trackBeatActors.msgs.AttachMsg;
import ass08.trackBeatActors.msgs.NewDataMsg;

import java.awt.*;

/**
 * Created by Luca on 25/06/16.
 */
public class ControllerActor extends UntypedActor{

    private ActorRef view;
    private ActorRef model;

    private int heartbeat_th;
    private int sec_th;

    private ControllerActor(Dimension dim, int heartbeat_th, int sec_th){
        this.heartbeat_th = heartbeat_th;
        this.sec_th = sec_th;
        this.model = getContext().actorOf(ModelActor.props(heartbeat_th,sec_th),"ModelActor");
        this.view = getContext().actorOf(ViewActor.props(dim,heartbeat_th,sec_th),"ViewActor");
    }

    /**
     * Crea Props per l'attore ControllerActor.
     * @return Props per la creazione di questo attore
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
        this.view.tell(new AttachMsg(),getSelf());
        this.model.tell(new AttachMsg(),getSelf());
    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof ActionMsg){
            switch (((ActionMsg) message).getAction()){
                case UPDATE_BHTH:
                    if (((ActionMsg) message).isRestore_update()){
                        this.heartbeat_th = ((ActionMsg) message).getValue();
                        this.model.tell(message,getSelf());
                    } else {
                        getSender().tell(new ActionMsg(EnumAction.UPDATE_BHTH,this.heartbeat_th),getSelf());
                    }
                    break;
                case UPDATE_SECTH:
                    if (((ActionMsg) message).isRestore_update()){
                        this.sec_th = ((ActionMsg) message).getValue();
                        this.model.tell(message,getSelf());
                    } else {
                        getSender().tell(new ActionMsg(EnumAction.UPDATE_SECTH,this.sec_th),getSelf());
                    }
                    break;
                default:
                    this.model.tell(message,getSelf());
                    break;
            }
        }

        if (message instanceof NewDataMsg){
            this.view.tell(message,getSelf());
        }
    }
}
